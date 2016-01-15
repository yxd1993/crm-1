Ext.application({
    name: "crm",
    appFolder: '../scripts/sales/LookContract',
    controllers: ["LookContract"],
    /*autoCreateViewport: true,*/
    autoCreateViewport: true,
    launch: function () {
        // 页面加载完成之后执行
    	// 如果cId不为空，则为修改客户信息
    	if(!Ext.isEmpty(cId)){
    		var params = {};
    		var saleContractEntity = {};
    		saleContractEntity.id = cId;
    		saleContractEntity.status = status;
    		params.saleContractEntity = saleContractEntity;
    		crm.requestJsonAjax('saleContractAction!showAttachment.action', params, 
    				function(response){
    					if(response.success){
    						var attachmentName = response.attachmentName;
    						var saleContractVo = response.saleContractVo;
    						if(attachmentName == null){
    							Ext.getCmp('uploadForm').setHidden(true);
    						}else{
    							// 组装参数
    							var form = Ext.getCmp('uploadForm').getForm();
    							// 审批状态基本信息
    							form.findField('attachmentName').setValue(attachmentName);
    							form.findField('createUser').setValue(saleContractVo.createUser);
    							form.findField('createDate').setValue(Ext.Date.format(new Date(saleContractVo.createDate),'Y-m-d H:i:s'));
    						}
    					}else{
        					Ext.MessageBox.alert('提示','查询合同附件失败'); 
        				}
        			}, 
        			function(){Ext.MessageBox.alert('提示','查询合同附件失败'); 
        		});
    		//获取上传的文件名
    		if(status == '1'){
    			Ext.getCmp('approvalForm').setHidden(true);
    			Ext.getCmp('contractBaseInfoForm').setHidden(true);
    			Ext.getCmp('contractSubmitInfoForm').setHidden(true);
    			Ext.getCmp('customerBaseInfoForm').setHidden(true);
    			Ext.getCmp('priceBaseInfoForm').setHidden(true);
    			Ext.getCmp('finInfoForm').setHidden(true);
    			Ext.getCmp('renewAccountForm').setHidden(true);
    			Ext.getCmp('attachmentInfoForm').setHidden(true);
    		}else if (status == '2'){
    			Ext.getCmp('contractBaseInfoForm').setHidden(true);
    			Ext.getCmp('contractSubmitInfoForm').setHidden(true);
    			Ext.getCmp('customerBaseInfoForm').setHidden(true);
    			Ext.getCmp('priceBaseInfoForm').setHidden(true);
    			Ext.getCmp('finInfoForm').setHidden(true);
    			Ext.getCmp('renewAccountForm').setHidden(true);
    			Ext.getCmp('attachmentInfoForm').setHidden(true);
    			crm.requestJsonAjax('saleContractAction!queryApprovalInfoById.action', params, 
        				function(response){
        					if(response.success){
        						var approvalEntity = response.approvalEntity;
        						// 组装参数
        						var form = Ext.getCmp('approvalForm').getForm();
        						// 审批状态基本信息
        						form.findField('contractId').setValue(approvalEntity.contractId);
        						form.findField('approvalDept').setValue(approvalEntity.approvalDept);
        						form.findField('approvalJob').setValue(approvalEntity.approvalJob);
        						form.findField('approvalEmpCode').setValue(approvalEntity.approvalEmpCode);
        						form.findField('approvalEmpName').setValue(approvalEntity.approvalEmpName);
        						form.findField('approvalTelephone').setValue(approvalEntity.approvalTelephone);
        					}else{
        						if(response.message == undefined){
                					Ext.MessageBox.alert('提示',"系统异常,请联系系统管理员!",toContractMain);
                				}else{
                					//业务异常
                					Ext.MessageBox.alert('提示',response.message,toContractMain);
                				} 
            				}
            			}, 
            			function(response){
            				if(response.message == undefined){
            					Ext.MessageBox.alert('提示',"系统异常,请联系系统管理员!",toContractMain);
            				}else{
            					//业务异常
            					Ext.MessageBox.alert('提示',response.message,toContractMain);
            				}; 
            		});
    		}else if (status == '3' || status == '5'){
    			Ext.getCmp('approvalForm').setHidden(true);
    			crm.requestJsonAjax('saleContractAction!querySaleContractInfoById.action', params, 
    				function(response){
    					if(response.success){
    						var saleContractEntity = response.saleContractEntity;
    						// 组装参数
    						var form = Ext.getCmp('lookContractViewId').getLookContractForm().getForm();
    						// 主键
    						form.findField('cId').setValue(cId);
    						// 合同基本信息
    						form.findField('title').setValue(saleContractEntity.title);
    						form.findField('workflowExplain').setValue(saleContractEntity.workflowExplain);
    						form.findField('emergencyDegree').setValue(saleContractEntity.emergencyDegree);
    						form.findField('workflowCode').setValue(saleContractEntity.workflowCode);
    						form.findField('applyDate').setValue(Ext.Date.format(new Date(saleContractEntity.applyDate),'Y-m-d'));
    						// 合同提交信息
    						form.findField('applyUser').setValue(saleContractEntity.applyUser);
    						form.findField('applyUserEmpCode').setValue(saleContractEntity.applyUserEmpCode);
    						form.findField('applyUserJobName').setValue(saleContractEntity.applyUserJobName);
    						form.findField('applyUserDeptName').setValue(saleContractEntity.applyUserDeptName);
    						form.findField('applyUserDeclareName').setValue(saleContractEntity.applyUserDeclareName);
    						form.findField('applyUserLogisticCode').setValue(saleContractEntity.applyUserLogisticCode);
    						form.findField('roadAreaCode').setValue(saleContractEntity.roadAreaCode);
    						form.findField('regionsCode').setValue(saleContractEntity.regionsCode);
    						form.findField('businessUnitCode').setValue(saleContractEntity.businessUnitCode);
    						form.findField('financeRegionsCode').setValue(saleContractEntity.financeRegionsCode);
    						form.findField('contractAdmin').setValue(saleContractEntity.contractAdmin);
    						form.findField('ifSaleManager').setValue(saleContractEntity.ifSaleManager);
    						form.findField('processType').setValue(saleContractEntity.processType);
    						form.findField('contractVersion').setValue(saleContractEntity.contractVersion);
    						form.findField('signAttribute').setValue(saleContractEntity.signAttribute);
    						// 客户基本信息
    						form.findField('dcAccount').setValue(saleContractEntity.dcAccount);
    						form.findField('enterpriseName').setValue(saleContractEntity.enterpriseName);
    						form.findField('customerNature').setValue(saleContractEntity.customerNature);
    						form.findField('industryCode').setValue(getDataDictionary().rendererSubmitToDisplay(saleContractEntity.industryCode, "CUSTOMER_INDUSTRY"));
    						form.findField('mainGoodsName').setValue(saleContractEntity.mainGoodsName);
    						form.findField('typeOfPackage').setValue(saleContractEntity.typeOfPackage);
    						//价格基本信息
    						form.findField('ddMonthCount').setValue(saleContractEntity.ddMonthCount);
    						form.findField('ddDiscount').setValue(saleContractEntity.ddDiscount);
    						form.findField('duDiscount').setValue(saleContractEntity.duDiscount);
    						form.findField('lowestValuationFee').setValue(saleContractEntity.lowestValuationFee);
    						form.findField('insuranceRate').setValue(saleContractEntity.insuranceRate);
    						form.findField('cargoServiceAgreement').setValue(saleContractEntity.cargoServiceAgreement);
    						form.findField('lowestShippingFee').setValue(saleContractEntity.lowestShippingFee);
    						form.findField('agreedDelivery').setValue(saleContractEntity.agreedDelivery);
    						form.findField('lowestDeliveryFee').setValue(saleContractEntity.lowestDeliveryFee);
    						form.findField('expense').setValue(saleContractEntity.expense);
    						form.findField('informationCost').setValue(saleContractEntity.informationCost);
    						form.findField('lowestFuel').setValue(saleContractEntity.lowestFuel);
    						form.findField('serviceAgreement').setValue(saleContractEntity.serviceAgreement);
    						form.findField('contractStartDate').setValue(Ext.Date.format(new Date(saleContractEntity.contractStartDate),'Y-m-d'));
    						form.findField('contractEndDate').setValue(Ext.Date.format(new Date(saleContractEntity.contractEndDate),'Y-m-d'));
    						form.findField('ifCod').setValue(saleContractEntity.ifCod);
    						form.findField('collectionChargesMin').setValue(saleContractEntity.collectionChargesMin);
    						form.findField('chargeRateMin').setValue(saleContractEntity.chargeRateMin);
    						//财务信息
    						form.findField('paymentMethod').setValue(saleContractEntity.paymentMethod);
    						form.findField('accountDescribe').setValue(saleContractEntity.accountDescribe);
    						form.findField('provideContractVersion').setValue(saleContractEntity.provideContractVersion);
    						form.findField('ifHaveNonstandardContractTerm').setValue(saleContractEntity.ifHaveNonstandardContractTerm);
    						form.findField('ifHaveNonstandardOperatTerm').setValue(saleContractEntity.ifHaveNonstandardOperatTerm);
    						form.findField('ifOpenOnlineOrder').setValue(saleContractEntity.ifOpenOnlineOrder);
    						//续约账号信息
    						form.findField('oldDdDiscount').setValue(saleContractEntity.oldDdDiscount);
    						form.findField('oldDuDiscount').setValue(saleContractEntity.oldDuDiscount);
    						form.findField('oldInsuranceRate').setValue(saleContractEntity.oldInsuranceRate);
    						form.findField('oldLowestValuationFee').setValue(saleContractEntity.oldLowestValuationFee);
    						form.findField('oldLowestDeliveryFee').setValue(saleContractEntity.oldLowestDeliveryFee);
    						form.findField('oldLowestShippingFee').setValue(saleContractEntity.oldLowestShippingFee);
    						form.findField('oldExpense').setValue(saleContractEntity.oldExpense);
    						form.findField('oldInformationCost').setValue(saleContractEntity.oldInformationCost);
    						form.findField('oldLowestFuel').setValue(saleContractEntity.oldLowestFuel);
    						form.findField('oldChargeRateMin').setValue(saleContractEntity.oldChargeRateMin);
    						//附件信息
    						form.findField('applyInstruction').setValue(saleContractEntity.applyInstruction);
    				}else{
        					Ext.MessageBox.alert('提示','查询合同信息失败'); 
        				}
        			}, 
        			function(){Ext.MessageBox.alert('提示','查询合同信息失败'); 
        		});
    		}
        }
    }
});

function toContractMain(){
	// 查看合同
	window.parent.delTabpanel('101001301');
	window.parent.delTabpanel('103004');
	var url = '/crm-web/sales/contractMain.action';
	window.parent.initTabpanel('103004', '合同列表', url, true);
}
