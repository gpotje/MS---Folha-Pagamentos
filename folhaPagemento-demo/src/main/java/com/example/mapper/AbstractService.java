package com.example.mapper;



import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractService {
	
	protected static final String ID_PATH_VARIABLE = "/{id:^(?!count|all|public).+}";
	
	@Autowired
	protected ModelMapper modelMapper;
	
	protected <D, T> D convertEntityToDTO(final T model, final Class<D> dtoClass) {
		return model != null ? modelMapper.map(model, dtoClass) : null;
	}
	
	protected <D,T> D convertToDTO(final T model, final Class<D> dtoClass) {
		return model != null ? modelMapper.map(model, dtoClass) : null ;
	}
	
	
	protected <D, T> List<D> convertToDTO(final Iterable<T> models, final Class<D> dtoClass) {
		List<D> dtos = new ArrayList<>();
		for (T model : models) {
			dtos.add(modelMapper.map(model, dtoClass));
		}

		return dtos;
	}
	
	public static Boolean ObjectIsNotNullOrIsNotEmpty(List<Object> obj) {
		if(obj == null || obj.isEmpty()) {
			return true;
		}
		return false;
	}

}
