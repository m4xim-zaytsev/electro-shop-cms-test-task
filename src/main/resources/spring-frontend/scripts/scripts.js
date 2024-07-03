$(document).ready(function() {
    function fetchData(category) {
        $.ajax({
            url: `/api/v1/main/${category}`,
            method: 'GET',
            success: function(data) {
                updateTable(data, category);
            }
        });
    }

    function updateTable(data, category) {
        const tableBody = $('#tableBody');
        tableBody.empty();

        data.forEach(item => {
            let row = '';
            if (category === 'electrotovary') {
                row = `<tr>
                        <td>${item.id}</td>
                        <td>${item.name || ''}</td>
                        <td>${item.description || ''}</td>
                        <td><button class="btn btn-primary">Action</button></td>
                    </tr>`;
            } else if (category === 'pokupki') {
                row = `<tr>
                        <td>${item.id}</td>
                        <td>${item.electroItem ? item.electroItem.name : ''}</td>
                        <td>${item.purchaseDate || ''}</td>
                        <td><button class="btn btn-primary">Action</button></td>
                    </tr>`;
            } else if (category === 'sotrudniki') {
                row = `<tr>
                        <td>${item.id}</td>
                        <td>${item.lastName ? item.lastName + ' ' + (item.firstName || '') : ''}</td>
                        <td>${item.positionType ? item.positionType.name : ''}</td>
                        <td><button class="btn btn-primary">Action</button></td>
                    </tr>`;
            }
            tableBody.append(row);
        });
    }

    $('#navElectroItem').click(function() {
        fetchData('electrotovary');
        $('#contentTitle').text('Электротовары');
    });

    $('#navPurchase').click(function() {
        fetchData('pokupki');
        $('#contentTitle').text('Покупки');
    });

    $('#navEmployee').click(function() {
        fetchData('sotrudniki');
        $('#contentTitle').text('Сотрудники');
    });

    // Load default category
    fetchData('electrotovary');
});
