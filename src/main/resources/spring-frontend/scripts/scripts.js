$(document).ready(function() {
    let currentCategory = 'electrotovary';
    let offset = 0;
    const limit = 5;

    function fetchData(category, offset, limit, append = false) {
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
            data: { offset, limit },
            success: function(data) {
                updateTable(data, category, append);
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
            switch (subcategory) {
                case 'position_type':
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    `);
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td><a href="/references/positiontypes/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'purchase_type':
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    `);
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td><a href="/references/purchasetypes/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'electro_type':
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Action</th>
                        </tr>
                    `);
                    data.forEach(item => {
                        const row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td><a href="/references/electrotypes/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'shop':
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Address</th>
                            <th>Action</th>
                        </tr>
                    `);
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
        } else {
            tableHeader.html(`
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Action</th>
                </tr>
            `);

            data.forEach(item => {
                let row = '';
                if (category === 'electrotovary') {
                    row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.name || ''}</td>
                            <td>${item.description || ''}</td>
                            <td><a href="/electrotovary/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                } else if (category === 'pokupki') {
                    row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.electroItemResponse ? item.electroItemResponse.name : ''}</td>
                            <td>${item.purchaseDate || ''}</td>
                            <td><a href="/pokupki/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                } else if (category === 'sotrudniki') {
                    row = `<tr>
                            <td>${item.id}</td>
                            <td>${item.lastName ? item.lastName + ' ' + (item.firstName || '') : ''}</td>
                            <td>${item.positionTypeResponse ? item.positionTypeResponse.name : ''}</td>
                            <td><a href="/sotrudniki/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                }
                tableBody.append(row);
            });
        }
    }

    function loadCategory(category) {
        currentCategory = category;
        offset = 0;
        if (category === 'references') {
            $('#subcategoryContainer').show();
        } else {
            $('#subcategoryContainer').hide();
        }
        fetchData(category, offset, limit);
        let title = '';
        switch (category) {
            case 'electrotovary':
                title = 'Электротовары';
                break;
            case 'pokupki':
                title = 'Покупки';
                break;
            case 'sotrudniki':
                title = 'Сотрудники';
                break;
            case 'references':
                title = 'Справочники';
                break;
        }
        $('#contentTitle').text(title);
        $('#loadMoreButton').data('category', category);

        // Убираем класс active у всех элементов и добавляем к текущему
        $('.nav-link').removeClass('active');
        $(`[data-category="${category}"]`).addClass('active');
    }

    $('#navElectroItem').click(function() {
        loadCategory('electrotovary');
    });

    $('#navPurchase').click(function() {
        loadCategory('pokupki');
    });

    $('#navEmployee').click(function() {
        loadCategory('sotrudniki');
    });

    $('#navReferences').click(function() {
        loadCategory('references');
    });

    $('#subcategorySelect').change(function() {
        offset = 0;
        fetchData('references', offset, limit);
    });

    $('#loadMoreButton').click(function() {
        offset += 1;
        fetchData(currentCategory, offset, limit, true);
    });

    // Load default category
    loadCategory('electrotovary');
});