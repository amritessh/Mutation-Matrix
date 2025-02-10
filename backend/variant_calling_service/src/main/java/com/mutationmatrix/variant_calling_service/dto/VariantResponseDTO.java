package com.mutationmatrix.variant_calling_service.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantResponseDTO {
    private List<VariantDTO> variants;

    public static class VariantDTO {
        private int position;
        private String reference;
        private String alternate;

    }
}
