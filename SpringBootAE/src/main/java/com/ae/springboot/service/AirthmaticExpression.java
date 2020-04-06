package com.ae.springboot.service;

import java.util.List;

import com.ae.springboot.RecordNotFoundException;
import com.ae.springboot.model.AeEntity;

/**
 * 
 * @author Kamlesh.Singh
 *
 */
public interface AirthmaticExpression {

	public int eval(String s);

	List<AeEntity> getAllAirthmeticExp() throws RecordNotFoundException;

	public AeEntity createOrUpdateAirthmeticExp(AeEntity entity) throws RecordNotFoundException;

	List<AeEntity> getAllAEOutputStartWithOne() throws RecordNotFoundException;

}
