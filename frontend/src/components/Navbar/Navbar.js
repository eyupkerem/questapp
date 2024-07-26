import React from 'react'
import { Link } from 'react-router-dom'

const Navbar = () => {
  const userId=5;
  return (
    <div className='navbar'>
      <ul>
      <li><Link to="/">Home</Link></li>
      <li><Link to={`/users/${userId}`}>User</Link></li>
      </ul>

    </div>
  )
}

export default Navbar