package com.dondoc.controller;

import com.dondoc.dto.ApiResponse;
import com.dondoc.dto.Categories;
import com.dondoc.dto.MonthlyHistories;
import com.dondoc.dto.RecordDto;
import com.dondoc.service.RecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    private final RecordService recordService;

    public RecordController(RecordService recordService){
        this.recordService = recordService;
    }

    @GetMapping
    public List<RecordDto.Record> getRecords() {
        return recordService.getRecords();
    }

    @GetMapping("/categories")
    public List<Categories> getCategories() {
        return recordService.getCategories();
    }

    @GetMapping("/monthly-history")
    public List<MonthlyHistories> getMonthlyHistory() {
        return recordService.getMonthlyHistories();
    }

    @PostMapping
    public void createRecord(@RequestBody RecordDto.Record record){
        recordService.createRecord(record);
    }

    @PostMapping("/categories")
    public void createCategory(@RequestBody Categories category){
        recordService.createCategory(category);
    }

    @PostMapping("/monthly-history")
    public void createMonthlyHistory(@RequestBody MonthlyHistories monthlyHistory){
        recordService.createMonthlyHistory(monthlyHistory);
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<ApiResponse<RecordDto.DeleteResponse>> deleteRecord(
            @RequestHeader(value = "userId", required = false) Long userId,
            @PathVariable Long recordId
    ) {
        RecordDto.DeleteResponse response = recordService.deleteRecord(userId, recordId);
        return ResponseEntity.ok(ApiResponse.ok(response, "거래 삭제 성공"));
    }
}
