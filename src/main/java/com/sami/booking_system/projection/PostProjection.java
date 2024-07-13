package com.sami.booking_system.projection;




public interface PostProjection {
    Long getPostId();
    String getPostTitle();
    String getPostContent();
    Long getAuthorId();
    String getAuthorName();
    Long getCommentId();
    String getCommentContent();
    Long getCommenterId();
    String getCommenterName();
}
