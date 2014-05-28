Ext.namespace('Ext.ss.grid');

Ext.ss.grid.EditorGridPanel = function(config) {
    this.addEvents({
        "noSelectRow": true,
        "newRowDataNoComplete": true,
        "saveRowDataNoComplete": true
    });
    Ext.ss.grid.EditorGridPanel.superclass.constructor.call(this, config);
};

Ext.extend(Ext.ss.grid.EditorGridPanel, Ext.grid.EditorGridPanel, {
    initEvents: function () {
        Ext.ss.grid.EditorGridPanel.superclass.initEvents.call(this);
        this.el.on("beforeedit", this.onBeforeEdit, this);
    },
    getSuccessFn : function(grid, callback, defaultCallback) {
    	var successFn = undefined;
    	if(Ext.isFunction(callback)){
			successFn = function(store, records, options){
				callback.call(grid, store, records, options);
			};
		} else {
			successFn = Ext.isFunction(defaultCallback)?defaultCallback:Ext.emptyFn;
		}
		return successFn;
    },
    getErrorFn : function(grid,store,errorHandler) {
    	var errorFn = undefined;
    	if(Ext.isFunction(errorHandler)) {
			errorFn = function(proxy, type, action, options, response, e){
				if(type=='response') {
					response = (response.responseText) ? Ext.decode(response.responseText) : response;
					errorHandler.call(grid, store, response, options, e);
				} else if(type=='remote') {
					errorHandler.call(grid, store, response, options);
				}
			};
		} else {
			errorFn = function(proxy, type, action, options, response, e){
				if(type=='response') {
					response = (response.responseText) ? Ext.decode(response.responseText) : response;
				}
				Ext.Msg.alert(response.title,response.message);
			};
		}
		return errorFn;
    },
    onBeforeEdit: function (e) {
    	e.grid.syncSize();
        return true;
    },
    isDirty: function() {
    	var store = this.getStore();
    	var records = store.getModifiedRecords();
    	return (records && records.length > 0)?true:false;
    },
    getPagingToolbar: function() {
    	var bbar = this.getBottomToolbar();
    	if(bbar) {
    		if(bbar.isXType('paging',true)){
				return bbar;
			} else {
				var paging = bbar.findByType('paging',true)[0];
				if(paging){
			        return paging;
				}
			}
    	}
		return undefined;
    },
    checkRowDataNoComplete: function(store, record, eventName){
    	if(typeof store.isValid === 'function') {
	    	var valid = store.isValid(false,this);
	    	if(valid === false) {
	    		var e = { grid: this};
		        this.fireEvent(eventName, e);
	    		return true;
	    	}
	    	return false;
    	}
    	return true;
    },
    add: function (record) {
    	var store = this.getStore();
    	var defaultValue = {};
    	record = Ext.value(record,{});
    	if(this.checkRowDataNoComplete(store,record,'newRowDataNoComplete')) { return; }
        
    	var checkField = store.fields.items;
        for (var i = 0; i < checkField.length; i++) {
        	defaultValue[checkField[i].name] = '';
        }
        
        Ext.applyIf(record,defaultValue);
        var extRecords = new store.recordType(record);
        extRecords.markDirty();
        store.afterEdit(extRecords,true);
        store.add(extRecords);
        var row = store.getCount()-1;
        var cm = this.getColumnModel();
        var colCount = cm.getColumnCount(true);
        for(var col=0 ; col<colCount ; col++){
	        if(cm.isCellEditable(col, row)){
	       		this.startEditing(row,col);
	       		break;
	        }
        }
    },
    load: function (parameters, callback, errorHandler) {
    	var grid = this;
    	var store = grid.getStore();
    	
    	var params = {};
		Ext.apply(params, parameters);
		Ext.apply(params, {method : 'search'});
		Ext.apply(store.baseParams,params);
		
		var paging = grid.getPagingToolbar();
		if(paging){
			var pn = paging.getParams();
	        if (!params[pn.start]) params[pn.start] = 0;
	        if (!params[pn.limit]) params[pn.limit] = paging.pageSize;
		}

		/**
		var successFn = undefined;
		if(Ext.isFunction(callback)){
			successFn = function(store, records, options){
				callback.call(this, store, records, options);
			};
		} else {
			successFn = function(store, records, options){
				if(records && records.length == 0) {
					Ext.Msg.alert(__messages.information,__messages.noDataFound);
				}
			};
		}
		store.addListener('load',successFn);
		
		var errorFn = undefined;
		if(Ext.isFunction(errorHandler)) {
			errorFn = function(proxy, type, action, options, response, e){
				function showRecords(store,response) {
					if(response && !Ext.isEmpty(response.data)) {
						store.loadData(response.data,false);
					}
				};
				
				if(type=='response') {
					response = (response.responseText) ? Ext.decode(response.responseText) : response;
					showRecords(store,response);
					errorHandler.call(this, store, response, options, e);
				} else if(type=='remote') {
					showRecords(store,response);
					errorHandler.call(this, store, response, options);
				}
			};
			store.addListener('exception',errorFn);
		} **/
		
		var successFn = this.getSuccessFn(grid, callback, function(store, records, options){
			if(records && records.length == 0) {
				Ext.Msg.alert(__messages.information,__messages.noDataFound);
			}
		});
		store.addListener('load',successFn);
		
		var errorFn =  this.getErrorFn(grid,store,errorHandler);
		store.addListener('exception',errorFn);
    	
    	store.load({
			params:params,
			callback : function(r,options,success){
				store.removeListener('load',successFn); delete successFn;
				store.removeListener('exception',errorFn); delete errorFn;
			}
		});
    },
    save : function(parameters, callback, errorHandler) {
    	var grid = this;
    	var store = grid.getStore();
    	store.save(grid);
    },
    remove : function(parameters, callback, errorHandler) {
    	var grid = this, store = grid.getStore(), sm = grid.getSelectionModel(), records = undefined;
        
		if(sm.hasSelection()) {
			records = sm.getSelections();
		} else {
			if(this.hasListener('noSelectRow')) { 
				var e = {grid: grid,cancel: false};
	            this.fireEvent('noSelectRow', e);
			} else {
				Ext.Msg.show({
					title:__messages.error,
					msg:__messages.pleaseSelectAtLeastOneRecord,
					buttons:Ext.Msg.OK
				});
			}
            return;
		}
    	
    	Ext.Msg.show({
			title:__messages.confirm,
			msg:__messages.confirmToDelete,
			buttons:Ext.Msg.OKCANCEL,
			fn:function(btn) {
				if(btn=='ok') {
					try {
						store.remove(records,true);
						grid.getView().refresh(false);
						if(Ext.isFunction(callback)) {
							callback.call(grid, store, records);
						}
					} catch(e) {
						if(Ext.isFunction(errorHandler)) {
							errorHandler.call(grid, store, records, undefined, e);
						} else {
							Ext.Msg.alert(__messages.error,e);
						}
					}
				}
			}
    	});
    }
    /**save: function (parameters, callback, errorHandler) {
    	var grid = this;
    	var store = this.getStore();
        var records = undefined;
        if(parameters&&parameters.records) {
            records = parameters.records;
            delete parameters.records;
        }
        else {
            records = store.getModifiedRecords();
        }
        
		if(this.checkRowDataNoComplete(store,records,'saveRowDataNoComplete')) {
            return;
        }

        var jsonCreateRecords = [];
        var jsonUpdateRecords = [];
        if(records && records.length>0) {
            for(var i = 0 ; i < records.length ; i ++) {
                if(records[i].phantom) {
					jsonCreateRecords.push(records[i].data);
				} else {
					jsonUpdateRecords.push(records[i].data);	
				}
            }
        }
        
        var params = {jsonCreateRecords : Ext.encode(jsonCreateRecords), jsonUpdateRecords : Ext.encode(jsonUpdateRecords)};
        Ext.apply(params, parameters);
        Ext.apply(params, {method : 'save'});

		var paging = this.getPagingToolbar();
		if(paging){
			var pn = paging.getParams();
	        params[pn.start] = 0;
	        params[pn.limit] = paging.pageSize;
		}

		var successFn = this.getSuccessFn(grid, callback, function() {
			Ext.Msg.alert(__messages.information, __messages.saveSuccessfully);
		});
		store.addListener('load',successFn);
		
		var errorFn =  this.getErrorFn(grid,store,errorHandler);
		store.addListener('exception',errorFn);
		
		store.load({
			params:params,
			callback : function(r,options,success){				
				store.removeListener('load',successFn); delete successFn;
				store.removeListener('exception',errorFn); delete errorFn;
			}
		});
    },
    saveNoLoad: function(parameters, callback, errorHandler){
    	var store = this.getStore();
		
		var records = undefined;
        if(parameters&&parameters.records) {
            records = parameters.records;
            delete parameters.records;
        }
        else {
            records = store.getModifiedRecords();
        }
		
		if(this.checkRowDataNoComplete(store,records,'saveRowDataNoComplete')) { 
			return; 
		}
        
        var jsonCreateRecords = [];
        var jsonUpdateRecords = [];
        if(records && records.length>0) {
            for(var i = 0 ; i < records.length ; i ++) {
                if(records[i].phantom) {
					jsonCreateRecords.push(records[i].data);
				} else {
					jsonUpdateRecords.push(records[i].data);	
				}
            }
        }
			
		var params = {jsonCreateRecords : Ext.encode(jsonCreateRecords), jsonUpdateRecords : Ext.encode(jsonUpdateRecords)};
		Ext.apply(params, parameters);
		Ext.apply(params, {method : 'save'});
		
		var saveNoCommitChanges = this.saveNoCommitChanges;
		
		var connection = store.proxy.getConnection();
		connection.request({
			url: store.proxy.url
			, params:params
			, success: function(response, options) {
				if(saveNoCommitChanges===false) {
					store.commitChanges();
				}
				if(Ext.isFunction(callback)){ 
					response = (response.responseText) ? Ext.decode(response.responseText) : response;
					callback.call(this, store, records, options, response);
				}
			}
			, failure: function(response, options) {
				response = (response.responseText) ? Ext.decode(response.responseText) : response;
				if(Ext.isFunction(errorHandler)) {
					errorHandler.call(this, store, response, options);
				} else {
					Ext.Msg.alert(response.title,response.message);
				}
			}
		});
    },
    removeRowNoLoad: function (parameters, callback, errorHandler) {
        var grid = this;
        var store = grid.getStore();
        
        var jsonArray = [];
        var jsonRecords = [];
        var phantomRecords = [];
        var records = undefined;
        
        if(parameters&&parameters.records) {
            records = parameters.records;
            delete parameters.records;
        }
        else {
            var sm = grid.getSelectionModel();
			if(sm.hasSelection()) {
				records = sm.getSelections();
			} else {
				if(this.hasListener('noSelectRow')) { 
					var e = {grid: grid,cancel: false};
		            this.fireEvent('noSelectRow', e);
				} else {
					
					Ext.Msg.show({
						title:__messages.error,
						msg:__messages.pleaseSelectAtLeastOneRecord,
						buttons:Ext.Msg.OK
					});
				}
	            return;
			}
        }
        
		Ext.Msg.show({
			title:__messages.confirm,
			msg:__messages.confirmToDelete,
			buttons:Ext.Msg.OKCANCEL,
			fn:function(btn) {
				if(btn=='ok') {
					if(records && records.length>0) {
				        for(var i = 0 ; i < records.length ; i++) {
							if(records[i].phantom) {
								phantomRecords.push(records[i]);
							} else {
								jsonArray.push(records[i].data);
								jsonRecords.push(records[i]);
							}
						}
			        }
					
					if(jsonArray.length > 0) {
						var params = {jsonRemoveRecords : Ext.util.JSON.encode(jsonArray)};
						Ext.apply(params, parameters);
						Ext.apply(params, {method : 'delete'});
						
						var connection = store.proxy.getConnection();
						connection.request({
							url: store.proxy.url
							, params:params
							, success: function(response, options) {
								if(phantomRecords.length > 0) { jsonRecords.push(phantomRecords); }
								store.remove(jsonRecords,true);
								grid.getView().refresh(false);
								if(Ext.isFunction(callback)){ 
									response = (response.responseText) ? Ext.decode(response.responseText) : response;
									callback.call(grid, store, records, options, response);
								}
							}
							, failure: function(response, options) {
								response = (response.responseText) ? Ext.decode(response.responseText) : response;
								if(Ext.isFunction(errorHandler)) {
									errorHandler.call(grid, store, response, options);
								} else {
									Ext.Msg.alert(response.title,response.message);
								}
							}
						});
					} else {
						try{
							store.remove(phantomRecords,true);
							grid.getView().refresh(false);
							if(Ext.isFunction(callback)) {
								callback.call(grid, store, phantomRecords);
							}
						}catch(e){
							if(Ext.isFunction(errorHandler)) {
								errorHandler.call(grid, store, phantomRecords, undefined, e);
							} else {
								Ext.Msg.alert(__messages.error,e);
							}
						}
					}
				}
			}
		});
    },
    removeRow: function (parameters, callback, errorHandler) {
        var grid = this;
        var store = this.getStore();
        
        var jsonRemoveRecords = [];
        var phantomRecords = [];
        var records = undefined;
        
        if(parameters&&parameters.records) {
            records = parameters.records;
            delete parameters.records;
        }
        else {
            var sm = this.getSelectionModel();
			if(sm.hasSelection()) {
				records = sm.getSelections();
			} else {
				if(this.hasListener('noSelectRow')) { 
					var e = {grid: grid,cancel: false};
		            this.fireEvent('noSelectRow', e);
				} else {
					Ext.Msg.show({
						title:__messages.error,
						msg:__messages.pleaseSelectAtLeastOneRecord,
						buttons:Ext.Msg.OK
					});
				}
	            return;
			}
        }
        
        Ext.Msg.show({
			title:__messages.confirm,
			msg:__messages.confirmToDelete,
			buttons:Ext.Msg.OKCANCEL,
			fn:function(btn) {
				if(btn=='ok') {
			        if(records && records.length>0) {
				        for(var i = 0 ; i < records.length ; i++) {
							if(records[i].phantom) {
								phantomRecords.push(records[i]);
							} else {
								jsonRemoveRecords.push(records[i].data);	
							}
						}
			        }
			  
					if(jsonRemoveRecords.length > 0) {
						var params = {jsonRemoveRecords : Ext.util.JSON.encode(jsonRemoveRecords)};
						Ext.apply(params, parameters);
						Ext.apply(params, {method : 'delete'});
						
						var paging = grid.getPagingToolbar();
						if(paging){
							var pn = paging.getParams();
					        params[pn.start] = 0;
					        params[pn.limit] = paging.pageSize;
						}
						
						var successFn = grid.getSuccessFn(grid, callback);
						store.addListener('load',successFn);
						var errorFn = grid.getErrorFn(grid, store, errorHandler);
						store.addListener('exception',errorFn);
						
						store.load({
							params:params,
							callback : function(r,options,success){
								store.removeListener('load',successFn); delete successFn;
								store.removeListener('exception',errorFn); delete errorFn;
							}
						});
					} else {
						try{
							store.remove(phantomRecords,true);
							grid.getView().refresh(false);
							if(Ext.isFunction(callback)) {
								callback.call(grid, store, phantomRecords);
							}
						}catch(e){
							if(Ext.isFunction(errorHandler)) {
								errorHandler.call(grid, store, phantomRecords, undefined, e);
							} else {
								Ext.Msg.alert(__messages.error,e);
							}
						}
					}
				}
			}
		});
    }**/
});
Ext.reg('ss-editorgrid', Ext.ss.grid.EditorGridPanel);