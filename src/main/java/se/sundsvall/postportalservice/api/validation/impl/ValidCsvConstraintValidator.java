package se.sundsvall.postportalservice.api.validation.impl;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;
import se.sundsvall.postportalservice.api.validation.ValidCsv;

public class ValidCsvConstraintValidator implements ConstraintValidator<ValidCsv, MultipartFile> {

	@Override
	public boolean isValid(final MultipartFile file, final ConstraintValidatorContext context) {
		return "text/csv".equals(file.getContentType());
	}
}
