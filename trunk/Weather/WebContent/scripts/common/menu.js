/* ================================================================ 
Create By SS-Group
=================================================================== */
Menu = {};

Menu.createMenu = function () {

	$.getJSON("menu.htm?method=getMenu", function (data) {
		Menu.treeview(data.menu);
	});
};

Menu.treeview = function (tData) {
	this.build = function (nodeInfo) {

		var node = $('<li><a class="fly">' + nodeInfo.text + '</a></li>');
		if (nodeInfo.text == 'Menu') {
			node = $('<li class="top"><a class="top_link"><span class="down">' + nodeInfo.text + '</span></a></li>');
		} else {
			if (nodeInfo.href !== undefined && nodeInfo.href !== null) {
				node = $('<li><a href="' + nodeInfo.href + '">' + nodeInfo.text + '</a></li>');
			} else {
				node = $('<li><a class="fly">' + nodeInfo.text + '</a></li>');
			}

		}

		var contents;
		if (nodeInfo.text == 'Menu') {
			contents = $('<ul class="sub"></ul>');
		} else {
			contents = $('<ul></ul>');
		}

		for (var item in nodeInfo.items) {
			if (typeof nodeInfo.items[item] == 'object') {
				sNode = this.build(nodeInfo.items[item]);
				contents.append(sNode);
			}
		}
		if (nodeInfo.href === undefined || nodeInfo.href === null) {
			node.append(contents);
		}

		return node;
	};

	this.tree = this.build(tData);
	$('#nav').append(this.tree);
};

$(document).ready(function () {
	Menu.createMenu();
});