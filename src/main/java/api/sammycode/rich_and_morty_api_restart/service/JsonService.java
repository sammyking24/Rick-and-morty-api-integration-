package api.sammycode.rich_and_morty_api_restart.service;

import api.sammycode.rich_and_morty_api_restart.entity.ApprovalStatus;
import api.sammycode.rich_and_morty_api_restart.entity.Character;

import static org.apache.coyote.http11.Constants.a;

public interface JsonService {
    String submitCharForApproval(int id);
    Character getCharFromJson(String blobId);
    void updateCharApprovalStatus(int id, ApprovalStatus approvalStatus);
}
