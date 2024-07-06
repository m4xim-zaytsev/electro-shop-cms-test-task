package com.example.store_cms.service;

import com.example.store_cms.mapper.*;
import com.example.store_cms.model.directory.ElectroType;
import com.example.store_cms.model.directory.PositionType;
import com.example.store_cms.model.directory.PurchaseType;
import com.example.store_cms.model.directory.Shop;
import com.example.store_cms.web.request.*;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import java.nio.file.Files;
import java.nio.file.Path;


@Slf4j
@Service
@RequiredArgsConstructor
public class ScvService {

    private final ShopService shopService;
    private final PurchaseTypeService purchaseTypeService;
    private final PositionTypeService positionTypeService;
    private final ElectroTypeService electroTypeService;
    private final EmployeeService employeeService;
    private final ElectroItemService electroItemService;
    private final PurchaseService purchaseService;
    private final ElectroShopService electroShopService;
    private final ElectroEmployeeService electroEmployeeService;

    private final EmployeeMapper employeeMapper;
    private final ElectroItemMapper electroItemMapper;
    private final PurchaseMapper purchaseMapper;
    private final ElectroShopMapper electroShopMapper;
    private final ElectroEmployeeMapper electroEmployeeMapper;

    public void processZipFile(MultipartFile file) throws IOException {
        Path tempDir = Files.createTempDirectory("");
        File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
        file.transferTo(tempFile);

        Map<String, File> extractedFiles = new HashMap<>();

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(tempFile))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                if (zipEntry.getName().endsWith(".csv")) {
                    File newFile = new File(tempDir.toFile(), zipEntry.getName());
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        byte[] buffer = new byte[1024];
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    }
                    extractedFiles.put(zipEntry.getName(), newFile);
                }
            }
        }

        // Define the processing order
        List<String> processingOrder = Arrays.asList(
                "Shop.csv",
                "PurchaseType.csv",
                "PositionType.csv",
                "ElectroType.csv",
                "Employee.csv",
                "ElectroItem.csv",
                "Purchase.csv",
                "ElectroShop.csv",
                "ElectroEmployee.csv"
        );

        // Process files in the defined order
        for (String fileName : processingOrder) {
            if (extractedFiles.containsKey(fileName)) {
                processCSV(extractedFiles.get(fileName));
            } else {
                log.warn("Expected file not found in ZIP: " + fileName);
            }
        }
    }

    private void processCSV(File csvFile) {
        try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(new FileInputStream(csvFile), "Windows-1251"))
                .withCSVParser(new CSVParserBuilder().withSeparator(';').withQuoteChar('"').build())
                .build()) {
            List<String[]> records = reader.readAll();
            String fileName = csvFile.getName();

            switch (fileName) {
                case "Shop.csv":
                    processShops(records);
                    break;
                case "PurchaseType.csv":
                    processPurchaseType(records);
                    break;
                case "PositionType.csv":
                    processPositionType(records);
                    break;
                case "ElectroType.csv":
                    processElectroType(records);
                    break;
                case "Employee.csv":
                    processEmployee(records);
                    break;
                case "ElectroItem.csv":
                    processElectroItem(records);
                    break;
                case "Purchase.csv":
                    processPurchase(records);
                    break;
                case "ElectroShop.csv":
                    processElectroShop(records);
                    break;
                case "ElectroEmployee.csv":
                    processElectroEmployee(records);
                    break;
                default:
                    log.warn("Unknown file: " + fileName);
            }
        } catch (IOException | CsvException | ParseException e) {
            log.error("Error reading CSV file", e);
        }
    }

    private void processElectroEmployee(List<String[]> records) throws ParseException {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            Long employeeId = Long.parseLong(record[0].trim());
            Long etype = Long.parseLong(record[1].trim());

            ElectroEmployeeRequest request = new ElectroEmployeeRequest();
            request.setElectroTypeId(etype);
            request.setEmployeeId(employeeId);


            electroEmployeeService.save(electroEmployeeMapper.requestToElectroEmployee(request));
        }
    }

    private void processElectroShop(List<String[]> records) throws ParseException {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            Long shopId = Long.parseLong(record[0].trim());
            Long electroItemId = Long.parseLong(record[1].trim());
            Integer count = Integer.parseInt(record[2].trim());

            ElectroShopRequest request = new ElectroShopRequest();
            request.setShopId(shopId);
            request.setElectroItemId(electroItemId);
            request.setCount(count);


            electroShopService.save(electroShopMapper.requestToElectroShop(request));
        }
    }

    private void processPurchase(List<String[]> records) throws ParseException {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            Long electroId = Long.parseLong(record[1].trim());
            Long employeeId = Long.parseLong(record[2].trim());
            Date purchaseDate = parsePurchaseDate(record[3].trim());
            Long typeId = Long.parseLong(record[4].trim());
            Long shopId = Long.parseLong(record[5].trim());

            PurchaseRequest request = new PurchaseRequest();
            request.setElectroItemId(electroId);
            request.setEmployeeId(employeeId);
            request.setPurchaseDate(purchaseDate);
            request.setPurchaseTypeId(typeId);
            request.setShopId(shopId);

            purchaseService.create(purchaseMapper.requestToPurchase(request),request.getElectroItemId(),
                    request.getEmployeeId(),request.getPurchaseTypeId(),request.getShopId());
        }
    }

    private void processElectroItem(List<String[]> records) throws ParseException {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String name = record[1].trim();
            Long etypeId = Long.parseLong(record[2].trim());
            Long price = Long.parseLong(record[3].trim());
            Integer count = Integer.parseInt(record[4].trim());
            Boolean archive = record[5].trim().equals("1")? true: false;
            String description = record[6].trim();

            ElectroItemRequest request = new ElectroItemRequest();
            request.setName(name);
            request.setElectroTypeId(etypeId);
            request.setPrice(price);
            request.setCount(count);
            request.setArchive(archive);
            request.setDescription(description);

            electroItemService.create(electroItemMapper.requestToElectroItem(request), request.getElectroTypeId());
        }
    }
    private void processEmployee(List<String[]> records) throws ParseException {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            EmployeeRequest employeeRequest = new EmployeeRequest();
            String lastname = record[1].trim();
            String firstname = record[2].trim();
            String patronymic = record[3].trim();
            Date birthDate = parseDate(record[4].trim());
            String positionId = record[5].trim();
            String shopId = record[6].trim();
            Boolean gender = record[7].trim().equals("1")? true : false;

            employeeRequest.setLastName(lastname);
            employeeRequest.setFirstName(firstname);
            employeeRequest.setPatronymic(patronymic);
            employeeRequest.setBirthDate(birthDate);
            employeeRequest.setPositionId(Long.parseLong(positionId));
            employeeRequest.setShopId(Long.parseLong(shopId));
            employeeRequest.setGender(gender);

            employeeService.create(employeeMapper.requestToEmployee(employeeRequest), employeeRequest.getPositionId(), employeeRequest.getShopId());
        }
    }

    private void processElectroType(List<String[]> records) {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String name = record[1].trim();
            ElectroType electroType = new ElectroType();
            electroType.setName(name);
            electroTypeService.create(electroType);
        }
    }

    private void processPositionType(List<String[]> records) {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String name = record[1].trim();
            PositionType positionType = new PositionType();
            positionType.setName(name);
            positionTypeService.create(positionType);
        }
    }

    private void processPurchaseType(List<String[]> records) {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String name = record[1].trim();
            PurchaseType purchaseType = new PurchaseType();
            purchaseType.setName(name);
            purchaseTypeService.create(purchaseType);
        }
    }

    private void processShops(List<String[]> records) {
        boolean isFirstLine = true;
        for (String[] record : records) {
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }
            String name = record[2].trim();
            String address = record[1].trim();
            Shop shop = new Shop();
            shop.setName(name);
            shop.setAddress(address);
            shopService.create(shop);
        }
    }

    public Date parseDate(String dateStr) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = inputFormat.parse(dateStr);
        String formattedDateStr = outputFormat.format(date);
        return outputFormat.parse(formattedDateStr);
    }

    public static Date parsePurchaseDate(String dateStr) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

        Date date = inputFormat.parse(dateStr);
        String formattedDateStr = outputFormat.format(date);

        return outputFormat.parse(formattedDateStr);
    }


}
