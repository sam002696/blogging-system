package com.sami.booking_system.dto.Questionnaire;

import lombok.Data;
//import java.util.List;

@Data
public class QuestionnaireDTO {
    private String title;
    private String conductedBy;
    private GeneralInfoDTO generalInfo;
    private OrganizationDTO organization;
    private RawMaterialDTO rawMaterial;
    private ManufacturingDTO manufacturing;
    private MachineUsedDTO machineUsed;
    private SafetyMeasuresDTO safetyMeasures;
    private SafetyRelationsDTO safetyRelations;
    private HouseKeepingDTO houseKeeping;
    private ExistingMachinerySafetyDTO existingMachinerySafety;
    private ProductOutputDTO productOutput;
    private IdentificationDTO identification;
}
