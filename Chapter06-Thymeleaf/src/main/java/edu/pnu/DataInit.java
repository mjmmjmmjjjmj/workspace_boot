package edu.pnu;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import edu.pnu.domain.Board;
import edu.pnu.domain.Member;
import edu.pnu.persistence.BoardRepository;
import edu.pnu.persistence.MemberRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataInit implements ApplicationRunner {
	private final MemberRepository memberRepo;
	private final BoardRepository boardRepo;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		memberRepo.save(Member.builder().id("member1").password("1234").name("둘리")
					.role("ROLE_USER").build());
		memberRepo.save(Member.builder().id("member2").password("1234").name("도우너")
				.role("ROLE_ADMIN").build());
		
		for (int i = 1; i < 10L; i++) {
			boardRepo.save(Board.builder()
					.title("title1"+i)
					.writer("member1")
					.content("content1"+i)
					.build()
					);
		}
		for (int i = 1; i < 10L; i++) {
			boardRepo.save(Board.builder()
					.title("title2" + i)
					.writer("member2")
					.content("content2" + i)
					.build()
					);
		}
	}

}
