package com.ejerciciosmesa.tareas.models.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

	public Resource load(String filename) throws MalformedURLException;
	public String copy(MultipartFile file) throws IOException;
	public void delete(String filename);
	public Path getPath(String filename);
}

