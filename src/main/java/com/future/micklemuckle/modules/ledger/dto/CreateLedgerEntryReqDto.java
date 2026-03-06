package com.future.micklemuckle.modules.ledger.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.time.LocalDate;

/**
 * CreateLedgerEntryReqDto
 *
 * @author : future
 * @date : 2026-03-06
 */
@Getter
public class CreateLedgerEntryReqDto {

    @NotBlank
    private LocalDate entryDate;

    @NotBlank
    private String entryType;

    @NotBlank
    private int amount;

    @NotBlank
    private String title;

    private String memo;

    @NotBlank
    private Long categoryId;

    @NotBlank
    private Long paymentId;
}
