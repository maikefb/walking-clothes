import React from 'react';
import '../logo/logo.css'
import Clothes from '../../../assets/icons/clothes.svg'
import Exclude from '../../../assets/icons/exclude.svg'
import Walking from '../../../assets/icons/walking.svg'


export function Logo() {

    return (
            <div className="logo">
                <img src={Exclude} alt="" className="logo--image"/>
                <div className="logo--title"><img src={Walking} alt=""/>
                <img src={Clothes} alt=""/></div>
            </div>

    );
}
