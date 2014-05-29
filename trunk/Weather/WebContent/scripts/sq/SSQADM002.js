	Ext.override(Ext.data.Connection, {
        timeout:300000
});

Ext.onReady(function(){	
	
	var searchFunction = function(){
		if(!Ext.isEmpty(Ext.getCmp('stationcd').getValue()) && !Ext.isEmpty(Ext.getCmp('valcd').getValue()))
        	{
				loadGridPanel();
        	}
		else{
			Ext.Msg.alert("Please input requried field.!");
		}
	};
	
	var gennarateExcel = function(){
		if(!Ext.isEmpty(Ext.getCmp('stationcd').getValue()) && !Ext.isEmpty(Ext.getCmp('valcd').getValue()))
		{
			var form = Ext.getCmp('loadGridPanel ').getForm();
		    var el = form.getEl().dom;
		    var target = document.createAttribute("target");
		    target.nodeValue = "_blank";
		    el.setAttributeNode(target);
		    el.action = document.getElementById('context').value + '/SSQADM002.htm?method=genReport&valdata='+Ext.getCmp('valcd').getValue()+"&stationdata="+Ext.getCmp('stationcd').getValue()+"&time="+Ext.getCmp('time').getValue();
		    el.submit();	
		}
		else{
			Ext.Msg.alert("Please input requried field.!");
		}
	};

	var loadGridPanel = function(){
		var fieldValues = Ext.getCmp('loadGridPanel ').getForm().getFieldValues();
		fieldValues.valdata = Ext.getCmp('valcd').getValue();
		fieldValues.stationdata = Ext.getCmp('stationcd').getValue();
		fieldValues.time = Ext.getCmp('time').getValue();
//		fieldValues.a=Ext.getCmp("selector2").getValue();
	    Ext.getCmp('gridPanel').load(fieldValues, function (store, records, options) {
	        if (records.length === 0) {
	            Ext.Msg.alert('Error', 'No Data Found.');
	        }
	    }, function (store, response, options, e) {
	    	Ext.Msg.alert('Error','Time out 5 minute.');
	    });
	};
	
	
	var stationStore = new Ext.data.JsonStore({
		root : 'records',
		xtype : 'jsonstore',
		data : stationStoreData,
		fields : [
				{
					name : 'pmdentcd'
				},{
					name : 'pmdldesc'
				},{
					name : 'dispActivity',
					mapping: 'pmdentcd+" - "+obj.pmdldesc'
				}],
		pruneModifiedRecords : true,
		url : document.getElementById('context').value + '/SSQADM002.htm',
		messageProperty : 'message',
		storeId : 'stationStore'
	});
	
	var timeStore = new Ext.data.SimpleStore({
        fields: ['id', 'name'],
        data: [['00:00','00:00'],['01:00','01:00'],['02:00','02:00'],['03:00','03:00'],['04:00','04:00'],['05:00','05:00'],
        ['06:00','06:00'],['07:00','07:00'],['08:00','08:00'],['09:00','09:00'],['10:00','10:00'],['11:00','11:00'],
        ['12:00','12:00'],['13:00','13:00'],['14:00','14:00'],['15:00','15:00'],['16:00','16:00'],['17:00','17:00'],
        ['18:00','18:00'],['19:00','19:00'],['20:00','20:00'],['21:00','21:00'],['22:00','22:00'],['23:00','23:00']],
        sortInfo: {field: 'name', direction: 'ASC'}
    });
	
	var startDate = new Ext.form.DateField({
		fieldLabel : 'Start Date',
		id : 'startDate',
		name : 'startDate',
		width : 200,
		format : 'd/m/Y',
	});
	
	var endDate = new Ext.form.DateField({
		fieldLabel : 'End Date',
		id : 'endDate',
		name : 'endDate',
		width : 200,
		format : 'd/m/Y',
	});
	
	var startTime = new Ext.form.TimeField({
		fieldLabel : 'Start Time',
		id : 'startTime',
		name : 'startTime',
		format: 'H:i',
	    altFormats:'H:i',
	    increment: 60,
		width : 200,
	});
	
	var endTime = new Ext.form.TimeField({
		fieldLabel : 'End Time',
		id : 'endTime',
		name : 'endTime',
		format: 'H:i',
	    altFormats:'H:i',
	    increment: 60,
		width : 200,
	});
	
	var valStore = new Ext.data.JsonStore({
		root : 'records',
		xtype : 'jsonstore',
		data : valStoreData,
		fields : [
				{
					name : 'pmdentcd'
				},{
					name : 'pmdldesc'
				},{
					name : 'dispActivity',
					mapping: 'pmdentcd+" - "+obj.pmdldesc'
				}],
		pruneModifiedRecords : true,
		url : document.getElementById('context').value + '/SSQADM002.htm',
		messageProperty : 'message',
		storeId : 'valStore'
	});
	
	searchBtn = new Ext.Button({
		text:'Search',
		id: 'searchBtn',
		xtype: 'button',
		handler: searchFunction
	});
	
	genBtn = new Ext.Button({
		text:'Export',
		id: 'genBtn',
		xtype: 'button',
		handler: gennarateExcel
	});
	
	
	// ---------------- Grid ---------------
	var gridDataStore = new Ext.data.JsonStore({
		root: 'records',
		xtype: 'jsonstore',
		fields: ['awsdattm','station','awsprm','awsval'],
		pruneModifiedRecords: true,
		url: 'SSQADM002.htm',
		messageProperty: 'message',
		storeId: 'gridDataStore',
	});
	
	var columns = [{
		   id: 'DateColumn',
		   width: 25,
		   align: 'center',
		   dataIndex: 'awsdattm',
		   header: 'Date Time',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'stationColumn',
		   width: 15,
		   align: 'center',
		   dataIndex: 'station',
		   header: 'Station',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'ValiableColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'awsprm',
		   header: 'Valiable',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'ValueColumn',
		   width: 25,
		   align: 'center',
		   dataIndex: 'awsval',
		   header: 'Value',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   }
	                    ];
	
	var gridPanel = new Ext.ss.grid.EditorGridPanel({
		id: 'gridPanel',
		store: gridDataStore,
		columns: columns,
		anchor : '95%',
		viewConfig: {
			forceFit: true
		},
		stripeRows: true,
		enableHdMenu: false,
		height : 500,
		clicksToEdit : 1,
		bbar: new Ext.PagingToolbar({
			pageSize: 100,
			store: gridDataStore
		})
	});
	
//	var tagStore = new Ext.data.SimpleStore({
//        fields: ['id', 'name'],
//        data:JSON.stringify(valStoreData.records)
//    });
	
	
    new Ext.form.FormPanel({
    	id:'loadGridPanel ',
        title: 'ข้อมูลผลการตรวจอากาศอัตโนมัติ',
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
			items:[{
				msgTarget: 'under',
                allowAddNewData: true,
                id:'valcd',
                xtype:'superboxselect',
				addNewDataOnBlur : true, 
                fieldLabel: 'Value <font color="red">*</font>',
                width : 100,
                emptyText: 'select the value',
                resizable: true,
                name: 'tags',
                anchor:'100%',
                store: valStore,
                mode: 'local',
                displayField: 'pmdldesc',
                valueField: 'pmdentcd',
                extraItemCls: 'x-tag',
                
                listeners: {
                    beforeadditem: function(bs,v){
                        //console.log('beforeadditem:', v);
                        //return false;
                    },
                    additem: function(bs,v){
                        //console.log('additem:', v);
                    },
                    beforeremoveitem: function(bs,v){
                        //console.log('beforeremoveitem:', v);
                        //return false;
                    },
                    removeitem: function(bs,v){
                        //console.log('removeitem:', v);
                    },
                    newitem: function(bs,v, f){
						//console.log(f);
                        v = v.slice(0,1).toUpperCase() + v.slice(1).toLowerCase();
                        var newObj = {
                            id: v,
                            name: v
                        };
                        bs.addItem(newObj);
                    }
                }
             }
			       ,startDate
			       ,startTime
			       ,{
						msgTarget: 'under',
		                allowAddNewData: true,
		                id:'time',
		                xtype:'superboxselect',
						addNewDataOnBlur : true, 
		                fieldLabel: 'At Time',
		                width : 100,
		                emptyText: 'select the time',
		                resizable: true,
		                name: 'tags',
		                anchor:'100%',
		                store: timeStore,
		                mode: 'local',
		                displayField: 'name',
		                valueField: 'id',
		                extraItemCls: 'x-tag',
		                
		                listeners: {
		                    beforeadditem: function(bs,v){
		                        //console.log('beforeadditem:', v);
		                        //return false;
		                    },
		                    additem: function(bs,v){
		                        //console.log('additem:', v);
		                    },
		                    beforeremoveitem: function(bs,v){
		                        //console.log('beforeremoveitem:', v);
		                        //return false;
		                    },
		                    removeitem: function(bs,v){
		                        //console.log('removeitem:', v);
		                    },
		                    newitem: function(bs,v, f){
								//console.log(f);
		                        v = v.slice(0,1).toUpperCase() + v.slice(1).toLowerCase();
		                        var newObj = {
		                            id: v,
		                            name: v
		                        };
		                        bs.addItem(newObj);
		                    }
		                }
		             }
			      ]
		},{
			xtype : 'fieldset',
			columnWidth:0.5,
			border : false,
			items:[ {
				msgTarget: 'under',
                allowAddNewData: true,
                id:'stationcd',
                xtype:'superboxselect',
				addNewDataOnBlur : true, 
                fieldLabel: 'Station <font color="red">*</font>',
                width : 100,
                emptyText: 'select the station',
                resizable: true,
                name: 'tags',
                anchor:'100%',
                store: stationStore,
                mode: 'local',
                displayField: 'pmdldesc',
                valueField: 'pmdentcd',
                extraItemCls: 'x-tag',
                
                listeners: {
                    beforeadditem: function(bs,v){
                        //console.log('beforeadditem:', v);
                        //return false;
                    },
                    additem: function(bs,v){
                        //console.log('additem:', v);
                    },
                    beforeremoveitem: function(bs,v){
                        //console.log('beforeremoveitem:', v);
                        //return false;
                    },
                    removeitem: function(bs,v){
                        //console.log('removeitem:', v);
                    },
                    newitem: function(bs,v, f){
						//console.log(f);
                        v = v.slice(0,1).toUpperCase() + v.slice(1).toLowerCase();
                        var newObj = {
                            id: v,
                            name: v
                        };
                        bs.addItem(newObj);
                    }
                }
             }
			        ,endDate
			        ,endTime
			      ]
		},
		{
			xtype : 'fieldset',
			columnWidth:1,
			items:[gridPanel]
		}],
        buttons: [searchBtn,genBtn],
        buttonAlign :'center',
        renderTo:'panel'
    });

});