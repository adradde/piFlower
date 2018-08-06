import React from 'react';
import GridItem from '../GridItem'
import Card from '../../Card/Card'
import CardHeader from '../../Card/CardHeader'
import CardFooter from '../../Card/CardFooter'
import CardIcon from '../../Card/CardIcon'

import Icon from "@material-ui/core/Icon";
import dashboardStyle from "assets/jss/material-dashboard-react/views/dashboardStyle.jsx";
import withStyles from "@material-ui/core/styles/withStyles";

const Flower = (props) => {
    const { classes } = props;
    return (
        <GridItem xs={12} sm={6} md={3}>
            <Card>
                <CardHeader color="rose" stats icon>
                    <CardIcon color="rose">
                        <Icon>local_florist</Icon>
                    </CardIcon>
                    <h4 className={classes.cardCategory}>{props.name}</h4>
                    <h3 className={classes.cardTitle}>
                        {props.state.wetLevel} <small>{props.state.unit}</small>
                    </h3>
                </CardHeader>
                <CardFooter stats>
                    <div className={classes.stats}>
                        <a href="#pablo" onClick={e => e.preventDefault()}>
                            {props.category}
                        </a>
                    </div>
                </CardFooter>
            </Card>
        </GridItem>
    );
}

export default withStyles(dashboardStyle)(Flower)

// Props:
// - name 
// - category
// - state: { 
//      wetLevel
//      unit