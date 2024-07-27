import React from "react";
import { Link } from "react-router-dom";
import { Avatar, CardContent, InputAdornment, OutlinedInput } from "@mui/material";

const Comment = ({ text, userId, userName }) => {
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
        disabled
        id="outlined-adornment-amount"
        multiline
        inputProps={{ maxLength: 25 }}
        fullWidth
        value={text}
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
      />
    </CardContent>
  );
};

export default Comment;