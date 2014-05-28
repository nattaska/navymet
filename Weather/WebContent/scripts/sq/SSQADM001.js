/*!
 * Ext JS Library 3.2.1
 * Copyright(c) 2006-2010 Ext JS, Inc.
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){
	
	var Checkbox = new Ext.grid.CheckboxSelectionModel();
	
	var searchFunction = function(){
				loadGridPanel();
	};
	
	var modifyFunction = function() {
		var records = Ext.getCmp('gridPanel').getStore().getModifiedRecords();
		var jsonArray = [];

		if (records && records.length > 0) {
			for ( var i = 0; i < records.length; i++) {
				if (!Ext.isEmpty(records[i].data.password) && !Ext.isEmpty(records[i].data.name)) {
					jsonArray.push(records[i].data);
				} else {
					Ext.Msg.alert('Warning', 'All fields are required');
					return;
				}
			}
			var params = {
				method : 'modify',
				records : Ext.util.JSON.encode(jsonArray)
			};
			Ext.Ajax.request({
				url : document.getElementById('context').value + '/SSQADM001.htm',
				params : params,
				success : function(response, opts) {
					Ext.Msg.alert('Information', 'Modify Successful', function() {
						loadGridPanel();
					});
				},
				failure : function(response, opts) {
					var responseOject = Ext.util.JSON.decode(response.responseText);
					Ext.Msg.alert(responseOject.messageHeader, responseOject.message);
				}
			});
		}
	};
	
	var deleteFunction = function(){
		var m = Ext.getCmp('gridPanel').getSelectionModel().getSelections();
		if(m.length > 0){
			Ext.MessageBox.confirm('Message', 'Are you sure to delete ?',del);
		}
	};
	
	function del(btn)
	{
		if(btn == 'yes')
		{
			var records = Ext.getCmp('gridPanel').getSelectionModel().getSelections();
			var jsonArray = [];
			
			if(records && records.length > 0){
				for(var i=0; i<records.length; i++){
					if(!Ext.isEmpty(records[i].data.uid)){
						jsonArray.push(records[i].data);
					}else{
						records.remove(i);
					}
					
				}
				var params = {
						method:'delete',
						records: Ext.util.JSON.encode(jsonArray)
				};
				Ext.Ajax.request({
					url : document.getElementById('context').value + '/SSQADM001.htm',
					params: params,
					success: function(response, opts){
						var responseOject = Ext.util.JSON.decode(response.responseText);
						if(responseOject.success){
							Ext.Msg.alert('','Delete Successful',function(){
								loadGridPanel();
							});
						}
						else{
							Ext.Msg.alert('Warning','colud not delete !!!' + responseOject.errorMessages +' Please contact to Suport Team',function(){
								loadGridPanel();
							});
						}
					},
					failure: function(response, opts){
						var responseOject = Ext.util.JSON.decode(response.responseText);
						Ext.Msg.alert('Warning','Could not delete !!!!');
						Ext.Msg.alert(responseOject.messageHeader, responseOject.message);
					}
				});	
			}
		}
	}
	
	var loadGridPanel = function(){
		var fieldValues = Ext.getCmp('userForm').getForm().getFieldValues();
		fieldValues.id = Ext.getCmp('idTxt').getValue();
		fieldValues.name = Ext.getCmp('nameTxt').getValue();
		fieldValues.admin = Ext.getCmp('adminCombo').getValue();
	    Ext.getCmp('gridPanel').load(fieldValues, function (store, records, options) {
	        if (records.length === 0) {
	            Ext.Msg.alert('Error', 'No Data Found.');
	        }
	    }, function (store, response, options, e) {
	        Ext.Msg.alert(response.messageHeader, response.message);
	    });
	};

	var idTxt = new Ext.form.TextField({
        fieldLabel: 'Username',
        id:'idTxt',
        name: 'idTxt',
        allowBlank:false,
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'idTxt',
			maxlength : 10,
			minlenght : 0
		}
	});
	
	var nameTxt = new Ext.form.TextField({
        fieldLabel: 'Name',
        id:'nameTxt',
        name: 'nameTxt',
        allowBlank:false,
        width:200,
        xtype: "textfield",
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'nameTxt',
			maxlength : 100,
			minlenght : 0
		}
	});
	
	var passwordTxt = new Ext.form.TextField({
        fieldLabel: 'Password',
        id:'passwordTxt',
        name: 'passwordTxt',
        allowBlank:false,
        width:200,
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'passwordTxt',
			maxlength : 100,
			minlenght : 0
		}
	});
	
	comboboxValue = [
	    ['Y'],['N']
	];
	
    var store = new Ext.data.ArrayStore({
        fields: ['value'],
        data : comboboxValue
    });
 
    var adminCombo = new Ext.form.ComboBox({
    	fieldLabel: 'Admin',
        id:'adminCombo',
        name: 'adminCombo',
		store: store,
        displayField:'value',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        value:'N',
        selectOnFocus:true,
        width:145
    });
    
    var statusadminCombo = new Ext.form.ComboBox({
    	store: store,
    	triggerAction: 'all',
    	mode: 'local',
    	forceSelection: true,
    	displayField:'value',
    	id:'statusadminCombo'
    });
    
    searchBtn = new Ext.Button({
		text:'Search',
		id: 'searchBtn',
		xtype: 'button',
		handler: searchFunction
	});
    
    
 // ---------------- Grid ---------------
	var gridDataStore = new Ext.data.JsonStore({
		root: 'records',
		xtype: 'jsonstore',
		fields: ['uid','name','admin','password'],
		pruneModifiedRecords: true,
		url: 'SSQADM001.htm',
		messageProperty: 'message',
		storeId: 'gridDataStore',
	});
	
	var columns = [Checkbox,{
		   id: 'UseridColumn',
		   width: 15,
		   align: 'center',
		   dataIndex: 'uid',
		   header: 'Userid',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'NameColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'name',
		   header: 'Name',
		   xtype: 'gridcolumn',
		   editor : {
               xtype: 'textfield',
               allowBlank:false
           }
	   },{
		   id: 'AdminColumn',
		   width: 25,
		   align: 'center',
		   dataIndex: 'admin',
		   header: 'Admin',
		   menuDisabled: true,
		   xtype: 'gridcolumn',
		   editor:statusadminCombo
	   },{
		   id: 'PasswordColumn',
		   width: 25,
		   align: 'center',
		   dataIndex: 'password',
		   header: 'Password',
		   menuDisabled: true,
		   xtype: 'gridcolumn',
		   editor : {
               xtype: 'textfield',
               allowBlank:false
           }
	   }
	                    ];
	
	var gridPanel = new Ext.ss.grid.EditorGridPanel({
		id: 'gridPanel',
		store: gridDataStore,
		sm: Checkbox,
		columns: columns,
		anchor : '95%',
		viewConfig: {
			forceFit: true
		},
		stripeRows: true,
		enableHdMenu: false,
		height : 500,
		clicksToEdit : 1,
		
		tbar: [{
			text : 'Modify',
			iconCls : 'save',
			handler : modifyFunction
		},{
			text:'Delete',
			iconCls:'delete',
			handler: deleteFunction
		}],
		bbar: new Ext.PagingToolbar({
			pageSize: 20,
			store: gridDataStore
		}),
	});
	
    new Ext.form.FormPanel({
    	id:'userForm',
        title: 'Add user Form',
        url:document.getElementById('context').value + '/SSQADM001.htm',
        width:'1000',
        collapsible:true,
		labelAlign : 'right',
		labelWidth : 150,
		layout : 'column',
		items:[{
			xtype : 'fieldset',
			columnWidth:0.5,
			border : false,
			items:[idTxt
			       ,adminCombo
			      ]
		},{
			xtype : 'fieldset',
			columnWidth:0.5,
			border : false,
			items:[ nameTxt
			        ,passwordTxt
			      ]
		},{
			xtype : 'fieldset',
			columnWidth:1,
			items:[gridPanel]
		}],
		
        buttons: [{
            text: 'Add',
            handler:function(){
            	if(!Ext.isEmpty(Ext.getCmp('idTxt').getValue()) && !Ext.isEmpty(Ext.getCmp('nameTxt').getValue())
            		&& !Ext.isEmpty(Ext.getCmp('passwordTxt').getValue()) 
            		&& !Ext.isEmpty(Ext.getCmp('adminCombo').getValue()))
            	{
	            	Ext.getCmp('userForm').getForm().submit({
						success : function(form, action) {
					       Ext.MessageBox.show({
					           title: 'Success',
					           msg: 'Save Successfully!',
					           buttons: Ext.MessageBox.INFO,
					           icon: Ext.MessageBox.WARNING,
					           buttons: Ext.MessageBox.OK,
					           fn:function(){
					        	   Ext.getCmp('idTxt').reset();
					        	   Ext.getCmp('nameTxt').reset();
					        	   Ext.getCmp('passwordTxt').reset();
					        	   Ext.getCmp('adminCombo').setValue('Y');
					           }
					       });
						},
						failure : function(form, action) {
					       Ext.MessageBox.show({
					           title: 'Fail',
					           msg: 'Duplicate id!',
					           buttons: Ext.MessageBox.OK,
					           icon: Ext.MessageBox.WARNING
					       });
						}
					});
            	}
            }
        },searchBtn],
        buttonAlign :'center',
        renderTo:'panel'
    });

});