package com.bitpalette.api.services;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FollowService {
    private final Map<String, Set<String>> followersMap = new ConcurrentHashMap<>();

    public void follow(String follower, String target) {
        followersMap.computeIfAbsent(follower, k -> new HashSet<>()).add(target);
        System.out.println("[SOCIAL] >> " + follower + " ahora sigue a " + target);
    }

    public Set<String> getFollowing(String user) {
        return followersMap.getOrDefault(user, Collections.emptySet());
    }
}