import React from 'react';
import Menu from '@material-ui/core/Input/Input'
import Button from '@material-ui/core/Button/Button'

const menu = (props) => {

    return (
        <div>
            <p>Settings</p>
            <Input> </Input>
            <Button onClick={updateSetting}></Button>
        </div>
    );
}

const updateSetting = () => {
    
}

export default menu;