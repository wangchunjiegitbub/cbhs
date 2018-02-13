/*打印easyui的datagrid*/
/**/
function printEasyuiDataGrid(title, printGrid, printDiv) {
    var tableString = '<table cellspacing="0" class="print-body">';
    var frozenColumns = printGrid.datagrid('options').frozenColumns;  // 得到frozenColumns对象
    var columns = printGrid.datagrid('options').columns;    // 得到columns对象
    var nameList = '';
    // 载入title
    if (typeof columns != 'undefined' && columns != '') {
        var count = columns.length;
        var colspan = 0;
        for (var i = 0; i < columns[0].length; i++) {
            var col = columns[0][i].colspan;
            if (col != undefined) {
                colspan += col;
            }
            else {
                colspan += 1;
            }
        }
        tableString += '\n<tr><td colspan=' + colspan + ' class="print-title">' + title + '</td></tr>'
        $(columns).each(function (index) {
            tableString += '\n<tr>';
            if (typeof frozenColumns != 'undefined' && typeof frozenColumns[index] != 'undefined') {
                for (var i = 0; i < frozenColumns[index].length; ++i) {
                    if (!frozenColumns[index][i].hidden) {
                        tableString += '\n<th width="' + frozenColumns[index][i].width + '"';
                        if (typeof frozenColumns[index][i].rowspan != 'undefined' && frozenColumns[index][i].rowspan > 1) {
                            tableString += ' rowspan="' + frozenColumns[index][i].rowspan + '"';
                        }
                        if (typeof frozenColumns[index][i].colspan != 'undefined' && frozenColumns[index][i].colspan > 1) {
                            tableString += ' colspan="' + frozenColumns[index][i].colspan + '"';
                        }
                        if (typeof frozenColumns[index][i].field != 'undefined' && frozenColumns[index][i].field != '') {
                            nameList += ',{"f":"' + frozenColumns[index][i].field + '", "a":"' + frozenColumns[index][i].align + '"}';
                        }
                        tableString += '>' + frozenColumns[0][i].title + '</th>';
                    }
                }
            }
            for (var i = 0; i < columns[index].length; ++i) {
                if (!columns[index][i].hidden) {
                    tableString += '\n<th width="' + columns[index][i].width + '"';
                    if (typeof columns[index][i].rowspan != 'undefined' && columns[index][i].rowspan > 1) {
                        tableString += ' rowspan="' + columns[index][i].rowspan + '"';
                    }
                    if (typeof columns[index][i].colspan != 'undefined' && columns[index][i].colspan > 1) {
                        tableString += ' colspan="' + columns[index][i].colspan + '"';
                    }
                    if (typeof columns[index][i].field != 'undefined' && columns[index][i].field != '') {
                        nameList += ',{"f":"' + columns[index][i].field + '", "a":"' + columns[index][i].align + '"}';
                    }
                    tableString += '>' + columns[index][i].title + '</th>';
                }
            }
            tableString += '\n</tr>';
        });
    }
    // 载入内容
    var rows = printGrid.datagrid("getRows"); // 这段代码是获取当前页的所有行
    var nl = eval('([' + nameList.substring(1) + '])');
    for (var i = 0; i < rows.length; ++i) {
        tableString += '\n<tr>';
        $(nl).each(function (j) {

            tableString += '\n<td';
            if (nl[j].a != 'undefined' && nl[j].a != '') {
                tableString += ' style="text-align:' + nl[j].a + ';"';
            }
            tableString += '>';
            tableString += rows[i][nl[j].f];
            tableString += '</td>';
        });
        tableString += '\n</tr>';
    }
    tableString += '\n</table>';
    $(printDiv).empty().append(tableString);
    $(printDiv).show();
    $(printDiv).dialog({
        title: "打印",
        cache: false,
        modal: true,
        height:600
    });
}