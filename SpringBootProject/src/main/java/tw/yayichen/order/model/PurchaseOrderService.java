package tw.yayichen.order.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {

	@Autowired
	private PurchaseOrderRepository purchaseOrderRepository;

	public PurchaseOrder insert(PurchaseOrder p) {
		return purchaseOrderRepository.save(p);
	}

	public PurchaseOrder findById(int id) {
		Optional<PurchaseOrder> pOrderRep = purchaseOrderRepository.findById(id);

		if (pOrderRep.isPresent()) {
			return pOrderRep.get();
		}

		return null;
	}

	public List<PurchaseOrder> findAll() {
		return purchaseOrderRepository.findAll();
	}

	public Page<PurchaseOrder> findAllByPage(Pageable pageable) {
		return purchaseOrderRepository.findAll(pageable);
	}
}
