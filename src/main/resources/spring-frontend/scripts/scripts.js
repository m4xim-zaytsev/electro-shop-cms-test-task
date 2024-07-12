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
            let addUrl = '';
            switch (subcategory) {
                case 'position_type':
                    addUrl = '/references/position_type/add';
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
                            <td><a href="/references/position_type/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'purchase_type':
                    addUrl = '/references/purchase_type/add';
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
                            <td><a href="/references/purchase_type/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'electro_type':
                    addUrl = '/references/electro_type/add';
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
                            <td><a href="/references/electro_type/edit/${item.id}" class="btn btn-primary">Edit</a></td>
                        </tr>`;
                        tableBody.append(row);
                    });
                    break;
                case 'shop':
                    addUrl = '/references/shop/add';
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
            $('#addButton').attr('href', addUrl).show();
        } else {
            let addUrl = '';
            switch (category) {
                case 'electrotovary':
                    addUrl = '/electrotovary/add';
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    `);
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
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Purchase Date</th>
                            <th>Action</th>
                        </tr>
                    `);
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
                    tableHeader.html(`
                        <tr>
                            <th>Id</th>
                            <th>Name</th>
                            <th>Position</th>
                            <th>Action</th>
                        </tr>
                    `);
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
            $('#addButton').attr('href', addUrl).show();
        }
        setAddButtonLink(currentCategory); // Set the link for the Add button
    }

    function loadCategory(category) {
        currentCategory = category;
        offset = 0;
        fetchData(category, offset, limit);
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
        fetchData('references', 0, limit);
        setAddButtonLink('references');
    });

    $('#loadMoreButton').click(function() {
        offset += 1; // Increment offset by limit for pagination
        fetchData(currentCategory, offset, limit, true);
    });

    // Initial load
    loadCategory(currentCategory);
});