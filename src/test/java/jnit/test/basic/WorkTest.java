package jnit.test.basic;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.spring.config.Application;
import com.spring.enums.StatusWorkEnum;
import com.spring.model.Work;
import com.spring.repository.WorkRespository;

@SpringBootTest(classes = Application.class)
public class WorkTest {
	
	@Autowired
	private WorkRespository workRepo;
	
	@Test
	public void testGetListWorks() {
		List<Work> works = (List<Work>) workRepo.findAll();
		assertTrue(!works.isEmpty());
	}
	
	@Test
	@Rollback(false)
	public void testCreateWork() {
		Work savedWork = new Work();
		savedWork.setDeleted(false);
		savedWork.setWorkName("test create work");
		savedWork.setEndingDate(LocalDateTime.now());
		savedWork.setStartingDate(LocalDateTime.now());
		savedWork.setStatus(StatusWorkEnum.PLANING);
		workRepo.save(savedWork);
		assertNotNull(savedWork.getId());
	}
}
