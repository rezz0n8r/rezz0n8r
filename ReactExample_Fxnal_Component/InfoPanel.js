import React from 'react';
import './InfoPanel.css';

const InfoPanel = (props) => {
    return (
      <div className='info-panel'>
        {props.infoMsg}
      </div>
    );
  };
  
export default InfoPanel;