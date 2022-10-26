import React from 'react'
import {Route, Switch} from 'react-router-dom'
import ClaimDetails from "./ClaimDetails"
import ClaimForm from "./ClaimForm"
import ClaimList from "./ClaimList"
import Menu from "./Menu"
import NoMatch from "./NoMatch"
import RolesRoute from "./RolesRoute"
import SecretClaims from "./SecretClaims"

const Claims = () => (
    <>
    <Menu></Menu>
    <Switch>
        <Route exact path="/">
            <ClaimList />
        </Route>
        <Route exact path="/claims/new">
            <ClaimForm/>
        </Route>
        <Route path="/claims/:claimId">
            <ClaimDetails/>
        </Route>
        <RolesRoute path="/secret" roles={['admin']}>
            <SecretClaims/>
        </RolesRoute>
        <Route path="*">
            <NoMatch/>
        </Route>
    </Switch>
    </>
)

export default Claims