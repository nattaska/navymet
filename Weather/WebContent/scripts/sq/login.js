Ext.onReady(function(){

	var j_username = new Ext.form.TextField({
        fieldLabel: 'UserName',
        name: 'username',
        allowBlank:false,
        applyTo:'username'
	});
	
	var j_password = new Ext.form.TextField({
        fieldLabel: 'Password',
        name: 'j_password',
        allowBlank:false,
        applyTo:'j_password',
        listeners: {
  		  specialkey: function(field, e){
  		  if (e.getKey() == e.ENTER) {
  			if(!Ext.isEmpty(j_username.getValue()) && !Ext.isEmpty(j_password.getValue())){
  		      	paarameter.setValue(j_username.getValue()+'#'+j_password.getValue());
  		      	document.forms["logInForm"].submit();          
  		      }
  		    }
  		  }
	    },
	});
	
	var paarameter = new Ext.form.Hidden({
		name: 'j_username',
        applyTo:'j_username'
	});
	
	var submitBtn = new Ext.Button({
		text : 'Log in',
		renderTo:'submitbtn',
		scale:'medium',
		width:'100%',
        handler : function(){
	      if(!Ext.isEmpty(j_username.getValue()) && !Ext.isEmpty(j_password.getValue())){
	      	paarameter.setValue(j_username.getValue()+'#'+j_password.getValue());
	      	document.forms["logInForm"].submit();          
	      }
	  }
	});
	
	var clearBtn = new Ext.Button({
		text : 'Clear',
		renderTo:'clearbtn',
		scale:'medium',
		width:'100%',
        handler : function(){
	    	j_username.reset();
	    	j_password.reset();
	    	paarameter.setValue('');
	    }
	});
    
});