import { Component, OnInit } from '@angular/core';
import * as Highcharts from 'highcharts'


@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.scss']
})
export class ChartComponent implements OnInit {

  chartOptions: {};
  Highcharts = Highcharts;

  constructor() { }

  ngOnInit() {

    // Chart Body

    this.chartOptions = {
      

      title: {
          text: 'Todays trends'
      },
  
      subtitle: {
          text: 'On 25 May . 2019 . 09:41 PM'
      },
  
      plotOptions: {
          series: {
              cumulative: true,
              pointStart: Date.UTC(2021, 0, 1),
              pointIntervalUnit: 'day'
          }
      },
  
      rangeSelector: {
          enabled: false
      },
  
      tooltip: {
          pointFormat: '<span style="color:{series.color}">{series.name}</span>: <b>{point.y}</b> ({point.cumulativeSum})<br/>',
          changeDecimals: 2,
          valueDecimals: 2
      },
  
      xAxis: {
          minRange: 3 * 24 * 36e5,
          max: Date.UTC(2021, 0, 6)
      },
  
      series: [{
          data: [1, 2, 5, 10, 20, 50, 100, -100, 100, -100]
      }, {
          data: [100, -50, -15, 15, -50, -20, -30, 100, -100, 100]
      }]
  };

  }

}
