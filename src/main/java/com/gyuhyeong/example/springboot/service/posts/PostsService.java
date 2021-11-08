package com.gyuhyeong.example.springboot.service.posts;

import com.gyuhyeong.example.springboot.domain.posts.Posts;
import com.gyuhyeong.example.springboot.domain.posts.PostsRepository;
import com.gyuhyeong.example.springboot.web.dto.PostsListResponseDto;
import com.gyuhyeong.example.springboot.web.dto.PostsResponseDto;
import com.gyuhyeong.example.springboot.web.dto.PostsSaveRequestDto;
import com.gyuhyeong.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return  id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true) // @Transactional(readOnly = true) => 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선(등록,수정,삭제 기능이 전혀 없는 곳에서 사용)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)     // 람다식 사용  ===  .map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());  // Posts들을 Stream을 map을 통해 PostsListReponseDto 변환 -> List로 반환
    } 
    
}
