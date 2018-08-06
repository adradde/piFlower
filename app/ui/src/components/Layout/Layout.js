import React from 'react';
import Top from '@material-ui/core/AppBar/AppBar'
import TabBar from '@material-ui/core/Tabs/Tabs';
import Tab from '@material-ui/core/Tab/Tab';

export default (props) => {
    return (
        <header>
            <TabBar >
                <Tab active={true}>Tab</Tab>
                <Tab>Tab</Tab>
                <Tab>Tab</Tab>
            </TabBar>
        </header>
    )
}