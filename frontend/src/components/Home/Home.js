import React, { useEffect, useState } from 'react';
import Post from '../Post/Post';
import { Container, Box, Typography } from '@mui/material';

const Home = () => {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [postList, setPostList] = useState([]);

  useEffect(() => {
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
  }, []);

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
          backgroundColor: '#cfe8fc',
          height: '100vh',
        }}
      >
        {postList.map(post => (
          <Post key={post.id} title={post.title} text={post.text} />
        ))}
      </Container>
    );
  }
}

export default Home;
