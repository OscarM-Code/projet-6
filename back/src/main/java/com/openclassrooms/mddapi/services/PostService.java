package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final SubscriptionService subscriptionService;

    @Autowired
    public PostService(PostRepository postRepository, SubscriptionService subscriptionService) {
        this.postRepository = postRepository;
        this.subscriptionService = subscriptionService;
    }

    public List<PostDto> getPostsByUserSubscriptions(Long userId) {
        List<Long> themeIds = subscriptionService.getThemeIdsByUserId(userId);

        List<Post> posts = postRepository.findByThemeIds(themeIds);
        return posts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDto createPost(PostDto postDto, Long userId) {
        Post post = convertToEntity(postDto);
        LocalDateTime now = LocalDateTime.now();
        post.setUserId(userId);
        post.setCreatedAt(now);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    public PostDto getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with id: " + postId));

        return convertToDTO(post);
    }

    private PostDto convertToDTO(Post post) {
        PostDto postDTO = new PostDto();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setThemeId(post.getThemeId());
        postDTO.setCreatedAt(post.getCreatedAt());
        return postDTO;
    }

    private Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setThemeId(postDto.getThemeId());
        return post;
    }
}
