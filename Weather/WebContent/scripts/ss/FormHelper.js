var FormHelper = {};

/* The following section is using for handling previous page state */
FormHelper.getGridState = function(grid) {
	var gridState = {};
	var store = grid.getStore();
	Ext.apply(gridState, (store.lastOptions)?store.lastOptions.params:{});
	gridState[store.getLoadedParam()] = Ext.value(gridState[store.getLoadedParam()],false);
	return gridState;
};

FormHelper.submitTo = function(url, params, stateObject) {
	var children = [];
	
	if (params) {
		for (var paramName in params) {
			children[children.length] = {tag: 'input', type: 'hidden', name: paramName, value: params[paramName]};
		}
	}
	
	if (stateObject) {
		var escapedState = Url.encode(Ext.encode(stateObject));
		children[children.length] = {tag: 'input', type: 'hidden', name: '__pageState', value: escapedState};
	} else if(FormHelper.storePageStateObject && params && !params.__pageState) {
		var escapedState = FormHelper.storePageStateObject;
		children[children.length] = {tag: 'input', type: 'hidden', name: '__pageState', value: escapedState};
	}

	var navigatorForm = Ext.DomHelper.append (Ext.getBody(), {
		id: '__navigatorForm'
		, tag: 'form'
		, action: url
		, method: 'POST'
		, children: children
	});
	navigatorForm.submit();
};

FormHelper.loadPageState = Ext.emptyFn;
FormHelper.setStateToForm = function() {
	if (FormHelper.pageStateObject) {
		for (var id in FormHelper.pageStateObject) {
			var cmp = Ext.getCmp(id);
			if (cmp) {
				cmp.setValue(FormHelper.pageStateObject[id]);
			};
		}
	}
};

/* The following section is using for handling Dirty Form confirmation */
FormHelper.isPageDirty = undefined;
FormHelper.changePage = undefined;
FormHelper.unchangePage = undefined;
FormHelper.confirmDirtyPage = function(args) {
	if (FormHelper.isPageDirty && FormHelper.isPageDirty.call(this, args)) {
		Ext.Msg.show({
			title: __messages.confirm//'[Confirm]' 
			, msg: '[The modification is not yet saved. <br>All changes will be discarded. Confirm to continue ?]'
			, buttons: Ext.Msg.OKCANCEL
			, fn: function(btn) {
				if (btn == 'ok' && FormHelper.changePage) FormHelper.changePage.call(this, args);
				else if(FormHelper.unchangePage) FormHelper.unchangePage.call(this, args);
			}
		});
	}
	else {
		if(FormHelper.changePage) FormHelper.changePage(args);
	}
};

FormHelper.changeMenu = undefined;
FormHelper.confirmChangeMenu = function(url) {
	if (FormHelper.isPageDirty && FormHelper.isPageDirty.call(this)) {
		Ext.Msg.show({
			title: __messages.confirm//'[Confirm]'  
			, msg: '[The modification is not yet saved. <br>All changes will be discarded. Confirm to continue ?]'
			, buttons: Ext.Msg.OKCANCEL
			, fn: function(btn) {
				if (btn == 'ok' && FormHelper.changeMenu) FormHelper.changeMenu.call(this);
			}
		});
	}
	else {
		if(FormHelper.changeMenu) FormHelper.changeMenu.call(this);
	}
};

FormHelper.confirmChangeUrl = function(url) {
	if (FormHelper.isPageDirty && FormHelper.isPageDirty.call(this)) {
		Ext.Msg.show({
			title: __messages.confirm//'[Confirm]' 
			, msg: '[The modification is not yet saved. <br>All changes will be discarded. Confirm to continue ?]'
			, buttons: Ext.Msg.OKCANCEL
			, fn: function(btn) {
				if (btn == 'ok') {
					window.location.href = url;
				}
			}
		});
	}
	else {
		window.location.href = url;
	}
};

FormHelper.confirmSearchOperation = function(args) {
	if (FormHelper.isPageDirty && FormHelper.isPageDirty.call(this, args)) {
		Ext.Msg.show({
			title: __messages.confirm//'[Confirm]' 
			, msg: '[The modification is not yet saved. <br>All changes will be discarded. Confirm to continue ?]'
			, buttons: Ext.Msg.OKCANCEL
			, fn: function(btn) {
				if (btn == 'ok' && searchFunction) searchFunction.call(this, args);
			}
		});
	}
	else {
		if(searchFunction) searchFunction.call(this, args);
	}
};

FormHelper.preventCleanSave = function(args) {
	if (!FormHelper.isPageDirty || FormHelper.isPageDirty.call(this,args)) {
		saveFunction.call(this, args);
	}
	else {
		Ext.Msg.show({
			title: __messages.information//'[Information]'
			, msg: __messages.thereAreNoChangedData//'[There are no changed data.]'
			, buttons: Ext.Msg.OK
		});
	}
};

//var LanguageUtils = {};
//
//LanguageUtils.isLanguageUtils = function()
//{
//	return application.isLanguageUtils;
//};
//
//LanguageUtils.isMonolingual = function()
//{
//	return !application.isLanguageUtils;
//};
//
//LanguageUtils.displayIf = function(msg, display)
//{
//	if (display) return msg;
//	else return '';
//};
//
//LanguageUtils.displayIfLanguageUtils = function(msg)
//{
//	return LanguageUtils.displayIf(msg,LanguageUtils.isLanguageUtils());
//};
//
//LanguageUtils.displayIfMonolingual = function(msg)
//{
//	return LanguageUtils.displayIf(msg,LanguageUtils.isMonolingual());
//};
//
//LanguageUtils.displayByUserLanguage = function(englishMsg, localMsg) {
//	if (language.toLowerCase() == 'en_us') {
//		return englishMsg;
//	} else {
//		return localMsg;
//	}
//};

///******************************************************************************************************
// * MessageUtils
// * Tool for messages.
// ******************************************************************************************************/
//var MessageUtils = {};
//
///** 
// * @type String
// * @private
// */
//MessageUtils.REQUIRED_FIELD_SPAN = '<span class="requiredField"> *</span>'; 
//MessageUtils.REQUIRED_COLUMN_SPAN = '<span class="requiredField"> *</span>'; 
//
///**
// * Add <span> *</span> after message when 2nd parameter(display) is true.
// * @param String msg
// * @param boolean require
// * @return String
// * @public
// */
//MessageUtils.requiredFieldIf = function(msg, display) {
//	return msg + ((display)?MessageUtils.REQUIRED_FIELD_SPAN:'');
//};
//
///**
// * Add <span> *</span> after message
// * @param String
// * @return String
// * @public
// */
//MessageUtils.requiredField = function(msg) {
//	return MessageUtils.requiredFieldIf(msg,true);  
//};
//
///**
// * Add <span> *</span> after message when program is LanguageUtils.
// * @param String msg
// * @return String
// * @public
// */
//MessageUtils.requiredFieldIfLanguageUtils = function(msg) {
//	return MessageUtils.requiredFieldIf(msg,LanguageUtils.isLanguageUtils());
//};
//
///**
// * Add <span> *</span> after message when program is monolingual.
// * @param String msg
// * @return String
// * @public
// */
//MessageUtils.requiredFieldIfMonolingual = function(msg) {
//	return MessageUtils.requiredFieldIf(msg,LanguageUtils.isMonolingual());
//};
//
///**
// * Add <span> *</span> after header when 2nd parameter(display) is true
// * @param Object/String config
// * @param boolean display
// * @return Object
// * @public
// */
//MessageUtils.requiredColumnIf = function(config,display) {
//	if(typeof config === 'string'){
//        config = {name: config};
//    }
//    if(display===true) {
//		config.require = true;
//    }
//	return config;  
//};
//
///**
// * Add <span> *</span> after header
// * @param Object/String 
// * @return Object
// * @public
// */
//MessageUtils.requiredColumn = function(config) {
//	return MessageUtils.requiredColumnIf(config,true);
//};
//
///**
// * Add <span> *</span> after config when program is LanguageUtils.
// * @param Object/String
// * @return Object
// * @public
// */
//MessageUtils.requiredColumnIfLanguageUtils = function(config) {
//	return MessageUtils.requiredColumnIf(config,LanguageUtils.isLanguageUtils());
//};
//
///**
// * Add <span> *</span> after config when program is monolingual.
// * @param Object/String
// * @return Object
// * @public
// */
//MessageUtils.requiredColumnIfMonolingual = function(config) {
//	return MessageUtils.requiredColumnIf(config,LanguageUtils.isMonolingual());
//};
//
///**
// * Add <span> *</span> with display style
// * @param String msg
// * @param String id
// * @return String
// * @public
// */
//MessageUtils.requiredFieldWithDisplayStyle = function(msg, id, display) {
//	return msg + '<span id="'+ id +'" style="color:red; display:'+ ((display)?display:'inline') +';"> *</span>'; 
//};
//
///**
// * To hide <span> *</span> by id
// * @param String id
// * @public
// */
//MessageUtils.hideRequiredFieldById = function(id) {
//	Ext.get(id).setDisplayed('none');
//};
//
///**
// * To show <span> *</span> by id
// * @param String id
// * @public
// */
//MessageUtils.showRequiredFieldById = function(id) {
//	Ext.get(id).setDisplayed('inline');
//};
//
///**
// * Confirm before remove 
// * if you press 'OK' callback fn1
// * else callback fn2
// * @param Function
// * @param Function
// * @param Ext.grid.GridPanel [option] alert check selection before alert remove comfirm.
// * @public
// */
//MessageUtils.confirmRemove = function(fn1,fn2,grid) {
//	if(grid) {
//		var sm = grid.getSelectionModel();
//		if(!sm.hasSelection()) {
//			Ext.Msg.show({
//				title:__commonMessage['L0686'],
//				msg:__commonMessage['M0065'],
//				buttons:Ext.Msg.OK
//			});
//            return;
//		}
//	}
//	
//	Ext.Msg.show({
//		title:__commonMessage['L0084'],
//		msg:__commonMessage['M0051'],
//		buttons:Ext.Msg.OKCANCEL,
//		fn:function(btn) {
//			if(btn=='ok') {
//				if(fn1) fn1.call(this);
//			}
//			else{
//				if(fn2) fn2.call(this);
//			}
//		}
//	});
//};
//
///**
// * Confirm before save 
// * if you press 'OK' callback fn1
// * else callback fn2
// * @param Function
// * @param Function
// * @public
// */
//MessageUtils.confirmSave = function(fn1,fn2) {
//	Ext.Msg.show({
//		title:__commonMessage['L0084'],
//		msg:__commonMessage['M0052'],
//		buttons:Ext.Msg.OKCANCEL,
//		fn:function(btn) {
//			if(btn=='ok') {
//				if(fn1) fn1.call(this);
//			}
//			else{
//				if(fn2) fn2.call(this);
//			}
//		}
//	});
//};
//
