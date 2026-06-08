package com.dondoc.controller;

import com.dondoc.dto.ApiResponse;
import com.dondoc.service.RecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/records")
public class RecordDeleteController {

    private final RecordService recordService;

    public RecordDeleteController(RecordService recordService) {
        this.recordService = recordService;
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<ApiResponse<Long>> deleteRecord(
            @RequestHeader(value = "userId", required = false) Long userId,
            @PathVariable Long recordId
    ) {
        try {
            Long deletedRecordId = recordService.deleteRecord(userId, recordId);
            return ResponseEntity.ok(new ApiResponse<>(true, deletedRecordId, "거래 삭제 성공"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new ApiResponse<>(false, null, e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, null, e.getMessage()));
        } catch (SecurityException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ApiResponse<>(false, null, e.getMessage()));
        }
    }
}
