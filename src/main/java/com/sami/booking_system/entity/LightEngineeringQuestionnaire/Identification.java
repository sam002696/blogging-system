package com.sami.booking_system.entity.LightEngineeringQuestionnaire;


import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.FetchType;
import lombok.Data;

import java.util.List;


@Data
@Embeddable
public class Identification {
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Financial> financial;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Technical> technical;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Managerial> managerial;

    @ElementCollection(fetch = FetchType.EAGER)@Embedded
    private List<NeedPriority> needPriority;

    @Data
    @Embeddable
    public static class Financial {
        private String financialType;
    }

    @Data
    @Embeddable
    public static class Technical {
        private String technicalType;
    }

    @Data
    @Embeddable
    public static class Managerial {
        private String managerialType;
    }

    @Data
    @Embeddable
    public static class NeedPriority {
        private String priorityType;
    }
}
