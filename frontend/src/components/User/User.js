import React from 'react'
import { useParams } from 'react-router-dom'

const User = () => {
  const {userId} = useParams();


  return (
    <div>
      <h1>
      {userId}
      </h1>
    </div>
  )
}

export default User