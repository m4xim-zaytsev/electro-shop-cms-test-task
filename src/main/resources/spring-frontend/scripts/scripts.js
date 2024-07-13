$(document).ready(function() {
    let currentCategory = 'electrotovary';
    let currentPage = 1;
    const limit = 5;
    let totalPages = 1;
    let totalItems = 0;

    function fetchData(category, page, limit, append = false) {
        let url;
        if (category === 'references') {
            const subcategory = $('#subcategorySelect').val();
            url = `/api/v1/main/references/${subcategory}`;
        } else {
            url = `/api/v1/main/${category}`;
        }

        $.ajax({
            url: url,
            method: 'GET',
            data: { page, limit },
            success: function(data) {
                totalPages = data.totalPages; // Получите общее количество страниц из ответа сервера
                totalItems = data.totalItems; // Получите общее количество записей из ответа сервера
                updateTable(data.items, category, append);
                updatePagination(page, totalPages, totalItems);
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.error("Error fetching data:", textStatus, errorThrown);
            }
        });
    }

    function updateTable(data, category, append) {
        const tableBody = $('#tableBody');
        const tableHeader = $('#tableHeader');
        if (!append) {
            tableBody.empty();
        }

        if (category === 'references') {
            const subcategory = $('#subcategorySelect').val();
            let addUrl = '';
            switch (subcategory) {
                case 'position_type':
                    addUrl = '/references/position_type/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td><a href="/references/position_type/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'purchase_type':
                    addUrl = '/references/purchase_type/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td><a href="/references/purchase_type/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'electro_type':
                    addUrl = '/references/electro_type/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td><a href="/references/electro_type/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'shop':
                    addUrl = '/references/shop/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td>${item.address || ''}</td>
                            <td><a href="/references/shop/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                default:
                    break;
            }
            $('#addButtonLink').attr('href', addUrl).show();
        } else {
            let addUrl = '';
            switch (category) {
                case 'electrotovary':
                    addUrl = '/electrotovary/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td>${item.description || ''}</td>
                            <td><a href="/electrotovary/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'pokupki':
                    addUrl = '/pokupki/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Purchase Date</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.electroItemResponse ? item.electroItemResponse.name : ''}</td>
                            <td>${item.purchaseDate || ''}</td>
                            <td><a href="/pokupki/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'sotrudniki':
                    addUrl = '/sotrudniki/add';
                    tableHeader.html(
                        `<tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Position</th>
                            <th>Action</th>
                        </tr>`
                    );
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.lastName ? item.lastName + ' ' + (item.firstName || '') : ''}</td>
                            <td>${item.positionTypeResponse ? item.positionTypeResponse.name : ''}</td>
                            <td><a href="/sotrudniki/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
            }
            $('#addButtonLink').attr('href', addUrl).show();
        }
        setAddButtonLink(currentCategory); // Set the link for the Add button
    }

    function updatePagination(currentPage, totalPages, totalItems) {
        const pagination = $('#pagination');
        pagination.empty();

        for (let i = 1; i <= totalPages; i++) {
            const activeClass = i === currentPage ? 'active' : '';
            const pageItem = `<li class="page-item ${activeClass}"><a class="page-link" href="#">${i}</a></li>`;
            pagination.append(pageItem);
        }

        const itemsInfo = `<span class="ml-3">Всего записей: ${totalItems}</span>`;
        pagination.append(itemsInfo);

        $('.page-link').click(function(e) {
            e.preventDefault();
            const selectedPage = parseInt($(this).text());
            currentPage = selectedPage;
            fetchData(currentCategory, currentPage, limit);
        });
    }

    function loadCategory(category) {
        currentCategory = category;
        currentPage = 1;
        fetchData(category, currentPage, limit);
        setAddButtonLink(category); // Set the link for the Add button
        $('#contentTitle').text($('a[data-category="' + category + '"]').text());
        $('.nav-link').removeClass('active');
        $('a[data-category="' + category + '"]').addClass('active');
        if (category === 'references') {
            $('#subcategoryContainer').show();
        } else {
            $('#subcategoryContainer').hide();
        }
    }

    function setAddButtonLink(category) {
        let addButtonLink = $('#addButtonLink');
        let baseUrl = "/";
        switch (category) {
            case 'electrotovary':
                addButtonLink.attr('href', baseUrl + 'electrotovary/create');
                break;
            case 'pokupki':
                addButtonLink.attr('href', baseUrl + 'pokupki/create');
                break;
            case 'sotrudniki':
                addButtonLink.attr('href', baseUrl + 'sotrudniki/create');
                break;
            case 'references':
                let subcategory = $('#subcategorySelect').val();
                addButtonLink.attr('href', baseUrl + 'references/' + subcategory + '/create');
                break;
        }
    }

    // Event bindings
    $('.nav-link').click(function() {
        loadCategory($(this).data('category'));
    });

    $('#subcategorySelect').change(function() {
        fetchData('references', 1, limit);
        setAddButtonLink('references');
    });

    // Initial load
    loadCategory(currentCategory);
});
