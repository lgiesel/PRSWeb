package prs.domain.purchaserequest;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRequestLineItemRepository extends CrudRepository<PurchaseRequestLineItem, Integer> {

	List<PurchaseRequestLineItem> findAllByPurchaseRequestID(int purchaseRequestId);
}
