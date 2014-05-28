var gennarateExcel = function(){
		var form = Ext.getCmp('loadGridPanel').getForm();
	    var el = form.getEl().dom;
	    var target = document.createAttribute("target");
	    target.nodeValue = "_blank";
	    el.setAttributeNode(target);
	    el.action = document.getElementById('context').value + '/SSQADM005.htm?method=genReport&valdata='+Ext.getCmp('valcd').getValue()+"&time="+Ext.getCmp('time').getValue();
	    el.submit();
	};

	Ext.override(Ext.data.Connection, {
        timeout:300000
});
	
Ext.onReady(function(){
	
	var searchFunction = function(){
		loadGridPanel();
	};

	var loadGridPanel = function(){
		var fieldValues = Ext.getCmp('loadGridPanel').getForm().getFieldValues();
		fieldValues.valdata = Ext.getCmp('valcd').getValue();
		fieldValues.time = Ext.getCmp('time').getValue();
	    Ext.getCmp('gridPanel').load(fieldValues, function (store, records, options) {
	        if (records.length === 0) {
	            Ext.Msg.alert('Error', 'No Data Found.');
	        }
	    }, function (store, response, options, e) {
	    	Ext.Msg.alert('Error','Time out 30 sec.');
	    });
	};
	
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
		url : document.getElementById('context').value + '/SSQADM005.htm',
		messageProperty : 'message',
		storeId : 'valStore'
	});
	
	var uLattitudeTxt = new Ext.form.TextField({
        fieldLabel: 'Upper Lattitude',
        id:'uLattitudeTxt',
        name: 'uLattitudeTxt',
        allowBlank:false,
        width:200,
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'uLattitudeTxt',
			maxlength : 100,
			minlenght : 0
		},
		listeners: {
			blur : function(bs,v){				
				if(!Ext.isEmpty(this.getValue())){
					if('N' != this.getValue().substr(this.getValue().length - 1) && 'S' != this.getValue().substr(this.getValue().length - 1)){
						alert("invalid format \n Ex. Lower Lattitude : 12.0N \n Ex. Upper Lattitude : 12.0S ");
						this.focus('',20);
					}
				}
            },
		}
	});
	
	var lLattitudeTxt = new Ext.form.TextField({
        fieldLabel: 'Lower Lattitude',
        id:'lLattitudeTxt',
        name: 'lLattitudeTxt',
        allowBlank:false,
        width:200,
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'lLattitudeTxt',
			maxlength : 100,
			minlenght : 0
		},
		listeners: {
			blur : function(bs,v){				
				if(!Ext.isEmpty(this.getValue())){
					if('N' != this.getValue().substr(this.getValue().length - 1) && 'S' != this.getValue().substr(this.getValue().length - 1)){
						alert("invalid format \n Ex. Lower Lattitude : 12.0N \n Ex. Upper Lattitude : 12.0S ");
						this.focus('',20);
					}
				}
            },
		}
	});
	
	var lLontitudeTxt = new Ext.form.TextField({
        fieldLabel: 'Left Lontitude',
        id:'lLontitudeTxt',
        name: 'lLontitudeTxt',
        allowBlank:false,
        width:200,
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'lLontitudeTxt',
			maxlength : 100,
			minlenght : 0
		},
		listeners: {
			blur : function(bs,v){				
				if(!Ext.isEmpty(this.getValue())){
					if('W' != this.getValue().substr(this.getValue().length - 1) && 'E' != this.getValue().substr(this.getValue().length - 1)){
						alert("invalid format \n Ex. Left Lontitude : 12.0W \n Ex. Right Lontitude : 12.0E ");
						this.focus('',20);
					}
				}
            },
		}
	});
	
	var rLontitudeTxt = new Ext.form.TextField({
        fieldLabel: 'Right Lontitude',
        id:'rLontitudeTxt',
        name: 'rLontitudeTxt',
        allowBlank:false,
        width:200,
        autoCreate : {
			tag : 'input',
			type : 'text',
			name : 'rLontitudeTxt',
			maxlength : 100,
			minlenght : 0
		},
		listeners: {
			blur : function(bs,v){				
				if(!Ext.isEmpty(this.getValue())){
					if('W' != this.getValue().substr(this.getValue().length - 1) && 'E' != this.getValue().substr(this.getValue().length - 1)){
						alert("invalid format \n Ex. Left Lontitude : 12.0W \n Ex. Right Lontitude : 12.0E ");
						this.focus('',20);
					}
				}
            },
		}
	});
	
	var startDate = new Ext.form.DateField({
		fieldLabel : 'Start Date',
		id : 'startDate',
		name : 'startDate',
		allowBlank : false,
		width : 200,
		format : 'd/m/Y',
	});
	
	var endDate = new Ext.form.DateField({
		fieldLabel : 'End Date',
		id : 'endDate',
		name : 'endDate',
		allowBlank : false,
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
	
	var timeStore = new Ext.data.SimpleStore({
        fields: ['id', 'name'],
        data: [['00:00','00:00'],['01:00','01:00'],['02:00','02:00'],['03:00','03:00'],['04:00','04:00'],['05:00','05:00'],
        ['06:00','06:00'],['07:00','07:00'],['08:00','08:00'],['09:00','09:00'],['10:00','10:00'],['11:00','11:00'],
        ['12:00','12:00'],['13:00','13:00'],['14:00','14:00'],['15:00','15:00'],['16:00','16:00'],['17:00','17:00'],
        ['18:00','18:00'],['19:00','19:00'],['20:00','20:00'],['21:00','21:00'],['22:00','22:00'],['23:00','23:00']],
        sortInfo: {field: 'name', direction: 'ASC'}
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
		fields: ['wamdat','latitude','longtitude','wavht','wavdir','winspd','frcvlc','windir','wavmean','wavpeak','deagcoef','wavstr'],
		//fields: ['swsdattm'],
		
		pruneModifiedRecords: true,
		url: 'SSQADM005.htm',
		messageProperty: 'message',
		storeId: 'gridDataStore',
	});
	
	var columns = [{
		   id: 'wamdatColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'wamdat',
		   header: 'Date Time',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'latitudeColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'latitude',
		   header: 'Latitude',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'longtitudeColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'longtitude',
		   header: 'Longtitude',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'wavhtColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'wavht',
		   header: 'Wave Height',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'wavdirColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'wavdir',
		   header: 'Wave Direction',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'winspdColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'winspd',
		   header: 'Wind Speed',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'frcvlcColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'frcvlc',
		   header: 'Friction Velocity',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'windirColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'windir',
		   header: 'Wind Direction',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'wavmeanColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'wavmean',
		   header: 'Average Period',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'wavpeakColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'wavpeak',
		   header: 'Peak Period',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'deagcoefColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'deagcoef',
		   header: 'Drag Coefficient',
		   menuDisabled: true,
		   xtype: 'gridcolumn'
	   },{
		   id: 'wavstrColumn',
		   width: 35,
		   align: 'center',
		   dataIndex: 'wavstr',
		   header: 'Wave Stress',
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
	
	
	 new Ext.form.FormPanel({
	    	id:'loadGridPanel',
	        title: 'ข้อมูลผลการจำลองคลื่น',
	        url:document.getElementById('context').value + '/SSQADM005.htm',
	        width:'1000',
	        collapsible:true,
			labelAlign : 'right',
			labelWidth : 150,
			layout : 'column',
			items:[{
				xtype : 'fieldset',
				columnWidth:0.5,
				border : false,
				items:[lLattitudeTxt
				       ,lLontitudeTxt
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
				items:[uLattitudeTxt
				        ,rLontitudeTxt
				        ,endDate
				        ,endTime
				        ,{
							msgTarget: 'under',
			                allowAddNewData: true,
			                id:'valcd',
			                xtype:'superboxselect',
							addNewDataOnBlur : true, 
			                fieldLabel: 'Value',
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