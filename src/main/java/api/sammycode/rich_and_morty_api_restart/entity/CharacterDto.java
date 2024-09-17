package api.sammycode.rich_and_morty_api_restart.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.ManyToOne;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {
    private int id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private String image;
    private List<String> episode;
    private String url;
    private String created;
    @Data
    public static class CharacterCreateDTO{
        private String name;
        private String status;
        private String species;
        private String type;
        private String gender;
        private String image;
        private List<String> episode;
        private String url;
        private String created;

        @Data
        public static class OriginDto{
//            private int id;
            private String name;
            private String url;
        }
        @Data
        public static class LocationDto{
//            private int id;
            private String name;
            private String url;
        }
    }
    @Data
    public static class CharacterUpdateDTO{
        private ApprovalStatus approvalStatus;
    }
    public void setApprovalStatus(ApprovalStatus approvalStatus){
    }

}
