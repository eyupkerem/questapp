import React, { useState } from "react";
import { Link } from "react-router-dom";
import { Avatar, Button, CardContent, InputAdornment, OutlinedInput } from "@mui/material";

const CommentForm = ({ postId, userId, userName }) => {

    const [text,setText]=useState("")


    const saveComment=()=>{
        fetch("/comments",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
            },
            body:JSON.stringify({
                postId:postId,
                userId:userId,
                text:text,
            }),
        })
        .then((res)=>res.json())
        .catch((err)=>console.log(err))
    }

    const handleSubmit =()=>{
        saveComment();
        setText("");
    }

    const handleChange=(i)=>{
        setText(i);
    }





  return (
    <CardContent
      style={{
        display: "flex",
        flexWrap: "wrap",
        justifyContent: "flex-start",
        alignItems: "center",
      }}
    >
      <OutlinedInput
        style={{ color: "black", 
        backgroundColor: 'white',
        fontWeight:"bold"}}
        id="outlined-adornment-amount"
        multiline
        value={text}
        inputProps={{ maxLength: 25 }}
        fullWidth
        onChange={(i)=>handleChange(i.target.value)}
        startAdornment={
          <InputAdornment position="start">
            <Link
              style={{
                textDecoration: "none",
                boxShadow: "none",
                color: "black",
                fontWeight: "bold",
              }}
              to={{ pathname: '/users/' + userId }}
            >
              <Avatar
                aria-label="recipe"
                style={{
                  width: 32,
                  height: 32,
                }}
              >
                {userName.charAt(0).toUpperCase()}
              </Avatar>
            </Link>
          </InputAdornment>
        }
    

        endAdornment ={
            <InputAdornment position="end">
                <Button
                    variant="contained"
                    onClick={handleSubmit}>
                    Comment
                </Button>
            </InputAdornment>
        }
        
      />
    </CardContent>
  );
};

export default CommentForm;