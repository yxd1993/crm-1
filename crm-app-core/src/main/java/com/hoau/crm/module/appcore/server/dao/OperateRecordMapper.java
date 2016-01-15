package com.hoau.crm.module.appcore.server.dao;


import org.springframework.stereotype.Repository;

import com.hoau.crm.module.appcore.api.shared.domain.OperateRecord;

@Repository
public interface OperateRecordMapper {
	void saveRecord(OperateRecord record);
}
