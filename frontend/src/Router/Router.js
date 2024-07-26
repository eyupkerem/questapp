import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Home from '../components/Home/Home'
import User from '../components/User/User'
import Navbar from '../components/Navbar/Navbar'

const Router = () => {
  return (
    <>
    <BrowserRouter>
    <Navbar/>
      <Routes>
        <Route path='/' element={<Home/>} />
        <Route path='/users/:userId' element={<User/>} />
      </Routes>
    </BrowserRouter></>
  )
}

export default Router