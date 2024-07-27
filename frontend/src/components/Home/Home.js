import React, { useEffect, useState } from 'react';
import Post from '../Post/Post';
import PostForm from '../Post/PostForm';
import { Container, Box, Typography } from '@mui/material';

const Home = () => {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [postList, setPostList] = useState([]);

  const refreshPost=()=>{
        fetch("/posts")
      .then(res => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setPostList(result);
        },
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      );
  }

  useEffect(() => {
    refreshPost();
  }, [postList]);


  if (!isLoaded) {
    return (
      <div>
        LOADING.....
      </div>
    );
  } else if (error) {
    return (
      <div>
        Error
      </div>
    );
  } else {
    return (
      <Container
        sx={{
          display: 'flex',
          flexWrap: 'wrap',
          justifyContent: 'center',
          alignItems: 'center',
          backgroundColor: '#f0f5ff',
          height:'100vh'
        }}
      >
        <PostForm 
        key={2} 
        userId={1} 
        userName={"asdjkf"}
        refreshPost={refreshPost}/>
        {postList.map(post => (
          <Post key={post.id} 
          postId={post.id}
          likes={post.postLikes}
          userId={post.userId} 
          title={post.title} 
          text={post.text} 
          userName={post.userName}/>
        ))}
      </Container>
    );
  }
}

export default Home;
