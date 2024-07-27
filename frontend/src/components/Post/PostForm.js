import React, { useEffect, useState } from 'react'
import Card from '@mui/material/Card';
import { styled } from '@mui/material/styles';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import Avatar from '@mui/material/Avatar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import { red } from '@mui/material/colors';
import FavoriteIcon from '@mui/icons-material/Favorite';
import CommentIcon from '@mui/icons-material/Comment';
import { Link } from 'react-router-dom';
import { Alert, Button, InputAdornment, OutlinedInput, Snackbar } from '@mui/material';


const ExpandMore = styled((props) => {
    const { expand, ...other } = props;
    return <IconButton {...other} />;
  })(({ theme, expand }) => ({
    transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
    marginLeft: 'auto',
    transition: theme.transitions.create('transform', {
      duration: theme.transitions.duration.shortest,
    }),
  }));
  
  const Post = ({userId,userName,refreshPost}) => {
    const [text,setText]=useState("")
    const [title,setTitle]=useState("")
    const [isSent,setIsSent]=useState(false);

    const savePost=()=>{
        fetch("/posts",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
            },
            body:JSON.stringify({
                title:title,
                userId:userId,
                text:text,
            }),
        })
        .then((res)=>res.json())
        .catch((err)=>console.log(err))
    }

    const handleSubmit = () =>{
        savePost();
        setIsSent(true);
        setTitle("");
        setText("");
        refreshPost();
    }

    const handleTitle = (value) =>{
        setTitle(value);
        setIsSent(false);
    }
    const handleText = (value) =>{
        setText(value);
        setIsSent(false);
    }
    const handleClose = (event, reason) => {
      if (reason === 'clickaway') {
        return;
      }
  
      setIsSent(false);
    };


    return (
        <div className="post-container" >
        <Snackbar open={isSent} autoHideDuration={2500} onClose={handleClose}>
          <Alert onClose={handleClose} severity="success">
           Your post is sent!
          </Alert>
        </Snackbar>
        <Card sx={{ width: 800 ,textAlign:"left"}}>
        <CardHeader
          avatar={
          <Link to={`/users/${userId}`}               
            style={{
            textDecoration: 'none',
            color: 'white',
            boxShadow: 'none',
          }}>
            <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
              {userName.charAt(0).toUpperCase()}
            </Avatar>
          </Link>
          }
          title={<OutlinedInput
          id='outlined-adornment-amount'
          multiline
          placeholder='Title'
          inputProps={{maxLenght : 25}}
          fullWidth
          value={title}
          onChange={(i)=>handleTitle(i.target.value)}>
          </OutlinedInput>}
        />
        <CardContent>
          <Typography variant="body2" color="text.secondary">
            <OutlinedInput
               id='outlined-adornment-amount'
               multiline
               placeholder='Text'
               inputProps={{maxLenght : 250}}
               fullWidth
               value={text}
               onChange={(i)=>handleText(i.target.value)}
               endAdornment={
                <InputAdornment position='end'>
                    <Button
                    variant="contained"
                    onClick={handleSubmit}>
                        Post
                    </Button>
                </InputAdornment>
               }>
             </OutlinedInput>
          </Typography>
        </CardContent>

      </Card>
      </div>
    )

}

export default Post;