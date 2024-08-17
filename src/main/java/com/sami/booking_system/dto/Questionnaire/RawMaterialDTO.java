package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;

import java.util.List;

@Data
public class RawMaterialDTO {
    private String rawMaterialRequired;
    private List<MaterialSupplierDTO> materialSupplier;
    private String qualityTest;
    private String intendedTest;
    private List<JudgeRawMaterialDTO> judgeRawMaterial;

    @Data
    public static class MaterialSupplierDTO {
        private String supplierType;
    }

    @Data
    public static class JudgeRawMaterialDTO {
        private String judge;
    }
}
