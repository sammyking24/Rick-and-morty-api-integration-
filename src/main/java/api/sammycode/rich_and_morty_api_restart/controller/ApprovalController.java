package api.sammycode.rich_and_morty_api_restart.controller;

import api.sammycode.rich_and_morty_api_restart.entity.ApprovalStatus;
import api.sammycode.rich_and_morty_api_restart.entity.Character;
import api.sammycode.rich_and_morty_api_restart.service.JsonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval2")
public class ApprovalController {
    @Autowired
    private JsonService jsonService;

    @PostMapping("/submit/{id}")
    public ResponseEntity<String> submitCharForApproval(@PathVariable int id){
        String blobId = jsonService.submitCharForApproval(id);
        return ResponseEntity.ok(blobId);
    }
    @GetMapping("/character/{blobId}")
    public ResponseEntity<Character> getCharFromJson(@PathVariable String blobId){
        Character character =jsonService.getCharFromJson(blobId);
        return ResponseEntity.ok(character);
    }
    @PutMapping("/approval/{id}")
    public ResponseEntity<Object> updateCharApprovalStatus
            (@PathVariable int id, @RequestParam ApprovalStatus approvalStatus){
        jsonService.updateCharApprovalStatus(id, approvalStatus);
        String message = "Character has been updated";
        return ResponseEntity.ok(message);
    }
}
