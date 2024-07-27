import React, { useEffect, useRef, useState } from 'react'
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
import { Margin } from '@mui/icons-material';
import { Container } from '@mui/material';
import Comment from '../Comment/Comment';
import CommentForm from '../Comment/CommentForm';

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
  
  const Post = ({postId,title,text,userId,userName,likes}) => {
    const [expanded, setExpanded] = React.useState(false);
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [isLiked,setIsLiked] = useState(false);
    const [commentList,setCommentList]=useState([]);
    const [likeCount,setLikeCount]=useState(likes.length);
    const isInitialMount=useRef(true);
    const [likeId,setLikeId]=useState(null);


    const saveLike = () => {
      fetch("/likes", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          postId: postId,
          userId: userId,
        }),
      })
      .then((res) => res.json())
      .catch((err) => console.log(err));
    }

    const deleteLike = () => {
      fetch("/likes/" + likeId, {
        method: "DELETE",
      })
      .catch((err) => console.log(err));
    }
    


    const handleExpandClick = () => {
      setExpanded(!expanded);
      refreshComments();
      console.log(commentList);
    }; 

    const handleLike=()=>{
      setIsLiked(!isLiked);
      if(!isLiked){
        setLikeCount(likeCount + 1)
        saveLike();
      }
      else{
        setLikeCount(likeCount-1)
        deleteLike();
      }
    }

    const refreshComments=()=>{
      fetch("/comments?postId="+postId)
    .then(res => res.json())
    .then(
      (result) => {
        setIsLoaded(true);
        setCommentList(result);
      },
      (error) => {
        setIsLoaded(true);
        setError(error);
      }
    );
    }

    const checklikes =()=>{
      var likeControl = likes.find((like => like.userId == userId));
      if(likeControl != null){
        setLikeId(likeControl.id)
        setIsLiked(true);
      }
    }

    useEffect(() => {
      if(isInitialMount.current)
        isInitialMount.current=false;
      else
       refreshComments();
    }, [commentList]);

    useEffect(()=>{
      checklikes()
    },[])



    return (
        <div className="post-container" >
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
          title={title}
        />
        <CardContent>
          <Typography variant="body2" color="text.secondary">
            {text}
          </Typography>
        </CardContent>
        <CardActions disableSpacing>
          <IconButton aria-label="add to favorites" onClick={handleLike}>
            <FavoriteIcon style={isLiked ? {color:"red"} : null} />
          </IconButton>
           {likeCount}
          <ExpandMore
            expand={expanded}
            onClick={handleExpandClick}
            aria-expanded={expanded}
            aria-label="show more"
          >
            <CommentIcon />
          </ExpandMore>
        </CardActions>
        <Collapse in={expanded} timeout="auto" unmountOnExit>
          <Container fixed>

            {error ? "error" : 
              isLoaded ? commentList.map(commment => (
                <Comment userId={1} userName={"USER"} text={commment.text}/>)
              ) :
              "Loading"
            }

            <CommentForm userId={1} userName={"USER"} postId={postId}/>


          </Container>
        </Collapse>
      </Card>
      </div>
    )

}

export default Post;