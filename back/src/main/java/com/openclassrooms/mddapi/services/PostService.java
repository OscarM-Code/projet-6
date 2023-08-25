package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public PostDto createPost(PostDto postDto) {
        Post post = convertToEntity(postDto);
        Post savedPost = postRepository.save(post);
        return convertToDTO(savedPost);
    }

    private PostDto convertToDTO(Post post) {
        PostDto postDTO = new PostDto();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setContent(post.getContent());
        postDTO.setUserId(post.getUserId());
        postDTO.setThemeId(post.getThemeId());
        postDTO.setCreatedAt(post.getCreatedAt());
        return postDTO;
    }

    private Post convertToEntity(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setUserId(postDto.getUserId());
        post.setThemeId(postDto.getThemeId());
        return post;
    }
}
