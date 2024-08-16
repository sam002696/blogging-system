package com.sami.booking_system.entity.LightEngineeringQuestionnaire;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RawMaterial {
    private String rawMaterialRequired;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<MaterialSupplier> materialSupplier;

    private String qualityTest;
    private String intendedTest;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<JudgeRawMaterial> judgeRawMaterial;

    @Data
    @Embeddable
    public static class MaterialSupplier {
        private String supplierType;
    }

    @Data
    @Embeddable
    public static class JudgeRawMaterial {
        private String judge;
    }
}
