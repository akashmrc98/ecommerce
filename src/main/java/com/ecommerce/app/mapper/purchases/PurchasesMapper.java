package com.ecommerce.app.mapper.purchases;

import com.ecommerce.app.domain.Purchase;
import com.ecommerce.app.dto.PurchasesDto;

public interface PurchasesMapper {
	 Iterable<PurchasesDto> purchasesToPurchasesDto(Iterable<Purchase> purchases);
}
